package uk.co.tangentlabs.crm.actors.messages;

import java.util.List;

import uk.co.tangentlabs.crm.actors.setter.Setter;

public class MapMessage {
	final String[] row;
	final List<Setter> mapper;

	public MapMessage(String[] row, List<Setter> mapper) {
		this.row = row;
		this.mapper = mapper;
	}

	public String[] getRow() {
		return row;
	}

	public List<Setter> getMapper() {
		return mapper;
	}

}
