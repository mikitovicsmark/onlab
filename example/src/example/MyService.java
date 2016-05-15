package example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/app")
public class MyService {

	@GET
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO init() {
		ChessClock.init();
		return new LabelDTO("initialized");
	}
	
	@GET
	@Path("/whiteText")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO getWhiteText() {
		return new LabelDTO(""+ ChessClock.getWhiteText());
	}
	
	@GET
	@Path("/blackText")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO getBlackText() {
		return new LabelDTO(""+ ChessClock.getBlackText());
	}
	
	@GET
	@Path("/main")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO getMainText() {
		return new LabelDTO(""+ ChessClock.getText());
	}
	
	
	@GET
	@Path("/startButton")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO start() {
		ChessClock.startButton();
		return new LabelDTO("start button");
	}
	
	@GET
	@Path("/modeButton")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO mode() {
		ChessClock.modeButton();
		return new LabelDTO("mode button");
	}
	
	@GET
	@Path("/whiteButton")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO whiteButton() {
		ChessClock.whiteButton();
		return new LabelDTO("white button");
	}
	
	@GET
	@Path("/blackButton")
	@Produces(MediaType.APPLICATION_JSON)
	public LabelDTO blackButton() {
		ChessClock.blackButton();
		return new LabelDTO("black button");
	}

}
