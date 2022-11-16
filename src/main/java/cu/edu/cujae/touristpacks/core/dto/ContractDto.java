package cu.edu.cujae.touristpacks.core.dto;

import java.time.LocalDate;

public class ContractDto {

	protected int idContract;
	protected String contractTitle;
	protected LocalDate startDate;
	protected LocalDate endDate;
	protected LocalDate conciliationDate;

	public ContractDto() {
	}

	public ContractDto(String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate) {
		this.contractTitle = contractTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.conciliationDate = conciliationDate;
	}

	public ContractDto(int idContract, String contractTitle, LocalDate startDate, LocalDate endDate,
			LocalDate conciliationDate) {
		this(contractTitle, startDate, endDate, conciliationDate);
		this.idContract = idContract;
	}

	public int getIdContract() {
		return this.idContract;
	}

	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}

	public String getContractTitle() {
		return this.contractTitle;
	}

	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getConciliationDate() {
		return this.conciliationDate;
	}

	public void setConciliationDate(LocalDate conciliationDate) {
		this.conciliationDate = conciliationDate;
	}

}
