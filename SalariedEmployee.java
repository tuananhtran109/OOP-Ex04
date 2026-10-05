/****************/
// Mã số sinh viên: 202418834
// Họ tên: Trần Tuấn Anh
/****************/

public class SalariedEmployee extends Employee {
    private double monthlySalary;
    private double responsibilityAllowance;

    public SalariedEmployee(String employeeId, String fullName, double monthlySalary, double responsibilityAllowance) {
        super(employeeId, fullName);
        setSalaryAndAllowance(monthlySalary, responsibilityAllowance);
    }

    public SalariedEmployee(String employeeId, String fullName, String department, double monthlySalary, double responsibilityAllowance) {
        super(employeeId, fullName, department);
        setSalaryAndAllowance(monthlySalary, responsibilityAllowance);
    }

    private void setSalaryAndAllowance(double monthlySalary, double responsibilityAllowance) {
        if (monthlySalary < 0 || responsibilityAllowance < 0) {
            throw new IllegalArgumentException("Lương và phụ cấp không âm.");
        }
        this.monthlySalary = monthlySalary;
        this.responsibilityAllowance = responsibilityAllowance;
    }

    @Override
    public double calculateGrossPay() {
        return monthlySalary + responsibilityAllowance + getMonthlyBonus();
    }

    @Override
    public void displayPayrollInfo() {
        super.displayPayrollInfo();
        System.out.printf("  -> Lương tháng: %.0f, Phụ cấp: %.0f, Thưởng: %.0f%n", 
                monthlySalary, responsibilityAllowance, getMonthlyBonus());
    }
}
