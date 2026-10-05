/****************/
// Mã số sinh viên: 202418834
// Họ tên: Trần Tuấn Anh
/****************/

import java.util.ArrayList;
import java.util.List;

public class Payroll {
    private String period;
    private List<Employee> employees;

    public Payroll(String period) {
        this.period = period;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Nhân viên không hợp lệ.");
        }
        if (findEmployee(employee.getEmployeeId()) != null) {
            throw new IllegalArgumentException("Mã nhân sự đã tồn tại trong bảng lương.");
        }
        employees.add(employee);
    }

    public Employee findEmployee(String employeeId) {
        for (Employee emp : employees) {
            if (emp.getEmployeeId().equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    public double calculateTotalPayroll() {
        double total = 0;
        for (Employee emp : employees) {
            total += emp.calculateGrossPay();
        }
        return total;
    }

    public double calculatePayrollByDepartment(String department) {
        double total = 0;
        for (Employee emp : employees) {
            if (emp.getDepartment().equals(department)) {
                total += emp.calculateGrossPay();
            }
        }
        return total;
    }

    public Employee findHighestPaidEmployee() {
        if (employees.isEmpty()) {
            return null;
        }
        Employee highest = employees.get(0);
        for (int i = 1; i < employees.size(); i++) {
            if (employees.get(i).calculateGrossPay() > highest.calculateGrossPay()) {
                highest = employees.get(i);
            }
        }
        return highest;
    }

    public void displayPayroll() {
        System.out.println("--- BẢNG LƯƠNG KỲ: " + period + " ---");
        if (employees.isEmpty()) {
            System.out.println("Danh sách nhân sự rỗng.");
            return;
        }
        for (Employee emp : employees) {
            emp.displayPayrollInfo();
        }
        System.out.printf("TỔNG BẢNG LƯƠNG: %.0f%n", calculateTotalPayroll());
    }
}
