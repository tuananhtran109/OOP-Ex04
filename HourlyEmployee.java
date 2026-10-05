/****************/
// Mã số sinh viên: 202418834
// Họ tên: Trần Tuấn Anh
/****************/

public class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double workedHours;

    public HourlyEmployee(String employeeId, String fullName, double hourlyRate, double workedHours) {
        super(employeeId, fullName);
        setRateAndHours(hourlyRate, workedHours);
    }

    public HourlyEmployee(String employeeId, String fullName, String department, double hourlyRate, double workedHours) {
        super(employeeId, fullName, department);
        setRateAndHours(hourlyRate, workedHours);
    }

    private void setRateAndHours(double hourlyRate, double workedHours) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Đơn giá giờ không âm.");
        }
        if (workedHours < 0 || workedHours > 250) {
            throw new IllegalArgumentException("Số giờ làm hợp lệ từ 0 đến 250.");
        }
        this.hourlyRate = hourlyRate;
        this.workedHours = workedHours;
    }

    @Override
    public double calculateGrossPay() {
        double basePay;
        if (workedHours <= 160) {
            basePay = workedHours * hourlyRate;
        } else {
            basePay = 160 * hourlyRate + (workedHours - 160) * hourlyRate * 1.5;
        }
        return basePay + getMonthlyBonus();
    }

    @Override
    public void displayPayrollInfo() {
        super.displayPayrollInfo();
        System.out.printf("  -> Giờ làm: %.1f, Đơn giá: %.0f, Thưởng: %.0f%n", 
                workedHours, hourlyRate, getMonthlyBonus());
    }
}
