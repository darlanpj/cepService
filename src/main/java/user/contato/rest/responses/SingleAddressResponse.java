package user.contato.rest.responses;


import user.contato.model.Address;

public class SingleAddressResponse {

	private boolean success;
	private Address address;
	
	public SingleAddressResponse(boolean success, Address address) {
		this.success = success;
		this.address = address;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Address getPersons() {
		return address;
	}
	public void setPerson(Address address) {
		this.address = address;
	}
}
