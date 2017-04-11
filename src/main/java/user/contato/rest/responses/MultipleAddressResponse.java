package user.contato.rest.responses;


import user.contato.model.Address;

import java.util.List;


public class MultipleAddressResponse {

	private boolean success;
	private List<Address> address;
	
	public MultipleAddressResponse(boolean success, List<Address> address) {
		this.success = success;
		this.address = address;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<Address> getPersons() {
		return address;
	}
	public void setPersons(List<Address> persons) {
		this.address = persons;
	}
	
}
