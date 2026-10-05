/****************/
// Mã số sinh viên: 202418834
// Họ tên: Trần Tuấn Anh
/****************/

public class SalesEmployee extends Employee {
    private double baseSalary;
    private double salesRevenue;
    private double commissionRate;

    public SalesEmployee(String employeeId, String fullName, double baseSalary, double salesRevenue, double commissionRate) {
        super(employeeId, fullName);
        initValues(baseSalary, salesRevenue, commissionRate);
    }

    public SalesEmployee(String employeeId, String fullName, String department, double baseSalary, double salesRevenue, double commissionRate) {
        super(employeeId, fullName, department);
        initValues(baseSalary, salesRevenue, commissionRate);
    }

    private void initValues(double baseSalary, double salesRevenue, double commissionRate) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Lương cơ bản không âm.");
        }
        if (commissionRate < 0 || commissionRate > 0.3) {
            throw new IllegalArgumentException("Tỷ lệ hoa hồng nằm trong khoảng từ 0 đến 0.3.");
        }
        if (salesRevenue < 0) {
            throw new IllegalArgumentException("Doanh số không âm.");
        }
        this.baseSalary = baseSalary;
        this.salesRevenue = salesRevenue;
        this.commissionRate = commissionRate;
    }

    public void updateSalesRevenue(double additionalRevenue) {
        if (additionalRevenue < 0) {
            throw new IllegalArgumentException("Doanh số cập nhật không âm.");
        }
        this.salesRevenue += additionalRevenue;
    }

    @Override
    public double calculateGrossPay() {
        return baseSalary + (salesRevenue * commissionRate) + getMonthlyBonus();
    }

    @Override
    public void displayPayrollInfo() {
        super.displayPayrollInfo();
        System.out.printf("  -> Lương cơ bản: %.0f, Doanh số: %.0f, Tỷ lệ hoa hồng: %.2f, Thưởng: %.0f%n", 
                baseSalary, salesRevenue, commissionRate, getMonthlyBonus());
    }
}
