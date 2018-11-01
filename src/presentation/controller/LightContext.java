package presentation.controller;

public class LightContext {
	private Event event;
	private Object data;

	public LightContext(Event event, Object data){
		this.event = event;
		this.data = data;
	}

	public Event getEvent() {
		return event;
	}

	public Object getData() {
		return data;
	}
}
