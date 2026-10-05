/****************/
// Mã số sinh viên: 202418834
// Họ tên: Trần Tuấn Anh
/****************/

public abstract class Employee {
    private String employeeId;
    private String fullName;
    private String department;
    private double monthlyBonus;

    // Constructors
    public Employee(String employeeId, String fullName) {
        this(employeeId, fullName, "Unassigned");
    }

    public Employee(String employeeId, String fullName, String department) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân sự không rỗng.");
        }
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không rỗng.");
        }
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Phòng ban không rỗng.");
        }
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
        this.monthlyBonus = 0;
    }

    public String getEmployeeId() { return employeeId; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public double getMonthlyBonus() { return monthlyBonus; }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Phòng ban không rỗng.");
        }
        this.department = department;
    }

    // Nạp chồng addBonus
    public void addBonus(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Thưởng phải lớn hơn 0.");
        }
        this.monthlyBonus += amount;
    }

    public void addBonus(double amount, String reason) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Thưởng phải lớn hơn 0.");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung lý do không được rỗng.");
        }
        this.monthlyBonus += amount;
    }

    public void addBonus(double rate, double referenceAmount, String reason) {
        if (rate <= 0 || rate > 0.5) {
            throw new IllegalArgumentException("Tỷ lệ thưởng phải lớn hơn 0 và không quá 0.5.");
        }
        if (referenceAmount <= 0) {
            throw new IllegalArgumentException("Giá trị tham chiếu phải lớn hơn 0.");
        }
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Nội dung lý do không được rỗng.");
        }
        this.monthlyBonus += (rate * referenceAmount);
    }

    public void resetBonus() {
        this.monthlyBonus = 0;
    }

    public abstract double calculateGrossPay();
    
    public String getEmployeeType() {
        return this.getClass().getSimpleName();
    }

    public void displayPayrollInfo() {
        System.out.printf("Mã: %s | Tên: %s | Phòng: %s | Loại: %s | Tổng lương: %.0f%n",
            employeeId, fullName, department, getEmployeeType(), calculateGrossPay());
    }
}
