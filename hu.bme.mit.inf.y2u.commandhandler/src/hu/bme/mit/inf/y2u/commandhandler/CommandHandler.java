package hu.bme.mit.inf.y2u.commandhandler;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.yakindu.sct.model.sgraph.Declaration;
import org.yakindu.sct.model.sgraph.Scope;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.stext.stext.impl.EventDefinitionImpl;
import org.yakindu.sct.model.stext.stext.impl.OperationDefinitionImpl;
import org.yakindu.sct.model.stext.stext.impl.VariableDefinitionImpl;

/**
 * This class receives the transformation command, acquires the Yakindu model as
 * a resource then calls the YakinduToUppaal run method with the resource file.
 */

public class CommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		try {
			if (sel instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) sel;
				if (selection.getFirstElement() != null) {
					if (selection.getFirstElement() instanceof IFile) {
						IFile file = (IFile) selection.getFirstElement();
						ResourceSet resSet = new ResourceSetImpl();
						URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
						Resource resource;
						try {
							resource = resSet.getResource(fileURI, true);
						} catch (RuntimeException e) {
							return null;
						}


						if (resource.getContents() != null) {
							EObject content = resource.getContents().get(0);

							Map<String, List<Map<String, List<String>>>> interfaces = new HashMap<String, List<Map<String, List<String>>>>();
							
							if (content instanceof Statechart) {
								Statechart s = (Statechart) content;
								

								Pattern pattern = Pattern.compile("\\(name: (.*?)\\)");
								
								for (Scope scope : s.getScopes()) {
									boolean setType = false;
									Matcher matcher = pattern.matcher(scope.toString());
									if (matcher.find()) {
										
										String type = "";
										
										List<String> list = new ArrayList<String>();
										for (Declaration declaration : scope.getDeclarations()) {
											if (declaration.getName() != null) {
												
												if (declaration instanceof EventDefinitionImpl && !setType) {
													type = "Event";
													setType = true;
												} else if (declaration instanceof OperationDefinitionImpl && !setType) {
													type = "Operation";
													setType = true;
												} else if (declaration instanceof VariableDefinitionImpl && !setType) {
													type = "Variable";
													setType = true;
												}
												
												list.add(declaration.getName());
											}
										}
										List<Map<String,List<String>>> elements = interfaces.get(type);
										if (elements == null) {
											elements = new ArrayList<Map<String, List<String>>>();
										}
										Map<String, List<String>> tempMap = new HashMap<String, List<String>>();
										tempMap.put(matcher.group(1), list);
										elements.add(tempMap);
										interfaces.put(type, elements);
										
										
										
									}
								}
							}
							
							PrintWriter out = null;
							try {
								String stateMachine =  ((Statechart) content).getName().replaceAll("\\s+","") + "Statemachine";
								out = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("out.txt")), "UTF-8"));
								
								out.println("import org.yakindu.scr.RuntimeService;");
								out.println("import org.yakindu.scr.TimerService;");
								out.println("");
								
								out.println("public class App {");
								out.println("\tpublic static void main(String[] args) {");
								
								out.println("\t\t" + stateMachine + " statemachine= new " + stateMachine +"();");
								out.println("\t\tstatemachine.setTimer(new TimerService());");
								for( Map<String, List<String>> map : interfaces.get("Operation") ) {
									Set<String> keys = map.keySet();
									for ( String key : keys) {
										List<String> operations = map.get(key);
										for (String operation : operations) {
											out.println("\t\tstatemachine.getSCI"+key+"().getSCI"+key+"OperationCallback(new SCI"+key+"OperationCallback(){");
											out.println("\t\t\t@Override");
											out.println("\t\t\t public void "+operation+"() {");
											out.println("\t\t\t\t System.out.println(\"Operation\");");
											out.println("\t\t\t}");
											out.println("\t\t});");
										}
									}
								}
								
								out.println("");
								out.println("\t\tstatemachine.init()");
								out.println("\t\tstatemachine.enter()");
								out.println("\t\tRuntimeService.getInstance().registerStatemachine(statemachine, 100);");
								out.println("\t\tScanner scanner = new Scanner(System.in);");
								
								out.println("");
								out.println("\t\twhile(scanner.hasNext()) {");
								out.println("\t\t\tswitch (scanner.next()) {");
								
								for( Map<String, List<String>> map : interfaces.get("Event") ) {
									Set<String> keys = map.keySet();
									for ( String key : keys) {
										List<String> operations = map.get(key);
										for (String operation : operations) {
											out.println("\t\t\tcase \""+operation+"\":");
											out.println("\t\t\t\tstatemachine.getSCI"+key+"().getSCI"+key+"Display().raise"+operation.substring(0, 1).toUpperCase() + operation.substring(1)+"();");
											out.println("\t\t\t\tbreak;");
										}
									}
								}
								
								out.println("\t\t\t}");
								out.println("\t\t\tstatemachine.runCycle();");
								out.println("");
								
								for( Map<String, List<String>> map : interfaces.get("Variable") ) {
									Set<String> keys = map.keySet();
									for ( String key : keys) {
										List<String> operations = map.get(key);
										for (String operation : operations) {
											out.println("\t\t\tSystem.out.println(statemachine.getSCI"+key+"().getSCI"+key+"Display().get"+operation.substring(0, 1).toUpperCase() + operation.substring(1)+"());");
										}
									}
								}
								
								out.println("\t\t}");
								
								out.println("\t}");
								out.println("}");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} finally {
								if (out != null) {
									out.flush();
									out.close();
								}
							}
						}
						return null;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

}
