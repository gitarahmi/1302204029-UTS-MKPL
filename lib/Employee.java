package lib;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private Date joinDate;
	
	private boolean isForeigner;
	private Gender gender; //true = Laki-laki, false = Perempuan
	private enum Gender{
        Male,
        Female
    }

	private int salary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, Date joinDate, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void hitungMonthlySalary(int grade) {	
		if (grade == 1) {
			salary = 3000000;
			if (isForeigner) {
				salary = (int) (3000000 * 1.5);
			}
		}else if (grade == 2) {
			salary = 5000000;
			if (isForeigner) {
				salary = (int) (3000000 * 1.5);
			}
		}else if (grade == 3) {
			salary = 7000000;
			if (isForeigner) {
				salary = (int) (3000000 * 1.5);
			}
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
	YearMonth currentYearMonth = YearMonth.now();
	YearMonth joinYearMonth = YearMonth.from((TemporalAccessor) joinDate);
	int monthsWorkedInCurrentYear = currentYearMonth.getMonthValue() - joinYearMonth.getMonthValue();
	int monthsWorkedInTotal = (currentYearMonth.getYear() - joinYearMonth.getYear()) * 12 + monthsWorkedInCurrentYear;
		
		return TaxFunction.calculateTax(salary, otherMonthlyIncome, monthsWorkedInTotal, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
