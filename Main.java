/****************/
// Mã số sinh viên: 202418834
// Họ tên: Trần Tuấn Anh
/****************/

public class Main {
    public static void main(String[] args) {
        Payroll payroll = new Payroll("2026-09");
        
        System.out.println("============================================================");
        System.out.println("PHẦN 1: DỮ LIỆU HỢP LỆ VÀ ĐA HÌNH");
        System.out.println("============================================================");
        
        SalariedEmployee e1 = new SalariedEmployee("E001", "Nguyễn Minh An", "Đào tạo", 15000000, 2000000);
        e1.addBonus(1000000); // Thưởng cố định
        payroll.addEmployee(e1);
        
        HourlyEmployee e2 = new HourlyEmployee("E002", "Trần Thu Bình", "Hỗ trợ", 100000, 150);
        e2.addBonus(500000, "Thưởng chuyên cần"); // Thưởng có lý do
        payroll.addEmployee(e2);
        
        HourlyEmployee e3 = new HourlyEmployee("E003", "Lê Hoàng Chi", "Hỗ trợ", 100000, 170); // Vượt 160h
        payroll.addEmployee(e3);
        
        SalesEmployee e4 = new SalesEmployee("E004", "Phạm Quốc Dũng", "Kinh doanh", 8000000, 200000000, 0.05);
        e4.addBonus(0.02, 50000000, "Thưởng đạt chỉ tiêu"); // Thưởng theo tỷ lệ
        payroll.addEmployee(e4);
        
        payroll.displayPayroll();
        
        System.out.printf(">> Tổng quỹ lương phòng Hỗ trợ: %.0f\n", payroll.calculatePayrollByDepartment("Hỗ trợ"));
        Employee highest = payroll.findHighestPaidEmployee();
        System.out.printf(">> Nhân viên lương cao nhất: %s (%.0f)\n", highest.getFullName(), highest.calculateGrossPay());
        
        System.out.println("\n============================================================");
        System.out.println("PHẦN 2: KIỂM THỬ BIÊN VÀ KIỂM THỬ LỖI CHI TIẾT");
        System.out.println("============================================================");
        
        // Nhóm 1: Kiểm thử Validate dữ liệu đầu vào (Lỗi)
        testCase(1, "Họ tên không rỗng", 
                 "Tạo nhân viên với tên rỗng", 
                 () -> new SalariedEmployee("E005", "", 1000, 100), 
                 "Họ tên không rỗng.");
                 
        testCase(2, "Lương/Phụ cấp không âm", 
                 "Tạo SalariedEmployee với lương = -1500", 
                 () -> new SalariedEmployee("E006", "Test Lương Âm", -1500, 100), 
                 "Lương và phụ cấp không âm.");
                 
        testCase(3, "Đơn giá giờ không âm", 
                 "Tạo HourlyEmployee với đơn giá = -50000", 
                 () -> new HourlyEmployee("E007", "Test Giờ Âm", -50000, 100), 
                 "Đơn giá giờ không âm.");
                 
        testCase(4, "Số giờ làm hợp lệ [0, 250]", 
                 "Tạo HourlyEmployee với 251 giờ (Biên trên lỗi)", 
                 () -> new HourlyEmployee("E008", "Test Vượt Giờ", 100000, 251), 
                 "Số giờ làm hợp lệ từ 0 đến 250.");
                 
        testCase(5, "Tỷ lệ hoa hồng [0, 0.3]", 
                 "Tạo SalesEmployee với tỷ lệ hoa hồng = 0.4 (40%)", 
                 () -> new SalesEmployee("E009", "Test HH Cao", 5000000, 100000, 0.4), 
                 "Tỷ lệ hoa hồng nằm trong khoảng từ 0 đến 0.3.");

        // Nhóm 2: Kiểm thử Nghiệp vụ Payroll & Cập nhật dữ liệu (Lỗi)
        testCase(6, "Không trùng mã nhân sự", 
                 "Thêm nhân viên mã E001 (đã tồn tại) vào Payroll", 
                 () -> payroll.addEmployee(new SalariedEmployee("E001", "Clone", 1000, 100)), 
                 "Mã nhân sự đã tồn tại trong bảng lương.");
                 
        testCase(7, "Cập nhật doanh số hợp lệ", 
                 "Cộng thêm doanh số âm (-5 triệu) cho SalesEmployee", 
                 () -> e4.updateSalesRevenue(-5000000), 
                 "Doanh số cập nhật không âm.");
                 
        testCase(8, "Tiền thưởng hợp lệ", 
                 "Thưởng cho nhân viên số tiền âm (-1000)", 
                 () -> e1.addBonus(-1000), 
                 "Thưởng phải lớn hơn 0.");
                 
        testCase(9, "Tỷ lệ thưởng hợp lệ (0, 0.5]", 
                 "Thưởng tỷ lệ 0.6 (60%)", 
                 () -> e4.addBonus(0.6, 10000000, "Thưởng sai quy tắc"), 
                 "Tỷ lệ thưởng phải lớn hơn 0 và không quá 0.5.");

        // Nhóm 3: Kiểm thử Biên hợp lệ (Logic tính toán)
        testCase(10, "Biên giờ làm = 160", 
                 "Tính lương với đúng 160h (không nhân hệ số 1.5)", 
                 () -> {
                     HourlyEmployee emp = new HourlyEmployee("E010", "Biên 160", 100000, 160);
                     double pay = emp.calculateGrossPay();
                     if (pay != 16000000.0) throw new RuntimeException("Sai logic tính lương biên 160. Ra: " + pay);
                     System.out.printf("       -> Lương tính ra chính xác: %.0f\n", pay);
                 }, null);

        testCase(11, "Biên giờ làm = 161", 
                 "Tính lương với 161h (1h vượt được nhân hệ số 1.5)", 
                 () -> {
                     HourlyEmployee emp = new HourlyEmployee("E011", "Biên 161", 100000, 161);
                     double pay = emp.calculateGrossPay();
                     // 160*100k + 1*100k*1.5 = 16.150.000
                     if (pay != 16150000.0) throw new RuntimeException("Sai logic tính lương biên 161. Ra: " + pay);
                     System.out.printf("       -> Lương tính ra chính xác: %.0f\n", pay);
                 }, null);

        testCase(12, "Biên hoa hồng = 0.0", 
                 "Tạo SalesEmployee với tỷ lệ hoa hồng = 0 (Biên dưới hợp lệ)", 
                 () -> {
                     SalesEmployee emp = new SalesEmployee("E012", "Biên HH 0", 8000000, 50000000, 0.0);
                     double pay = emp.calculateGrossPay();
                     if (pay != 8000000.0) throw new RuntimeException("Sai logic tính hoa hồng 0. Ra: " + pay);
                     System.out.printf("       -> Lương tính ra chính xác: %.0f\n", pay);
                 }, null);
                 
        testCase(13, "Biên hoa hồng = 0.3", 
                 "Tạo SalesEmployee với tỷ lệ hoa hồng = 0.3 (Biên trên hợp lệ)", 
                 () -> {
                     SalesEmployee emp = new SalesEmployee("E013", "Biên HH 0.3", 8000000, 10000000, 0.3);
                     double pay = emp.calculateGrossPay();
                     if (pay != 11000000.0) throw new RuntimeException("Sai logic tính hoa hồng 0.3. Ra: " + pay);
                     System.out.printf("       -> Lương tính ra chính xác: %.0f\n", pay);
                 }, null);
                 
        testCase(14, "Xử lý tìm kiếm Null", 
                 "Tìm nhân viên có mã không tồn tại (E999)", 
                 () -> {
                     Employee emp = payroll.findEmployee("E999");
                     if (emp != null) throw new RuntimeException("Lỗi: Đáng ra phải trả về null!");
                     System.out.println("       -> Trả về null chính xác.");
                 }, null);
    }
    
    /**
     * Hàm hỗ trợ chạy test và in kết quả chi tiết
     * @param expectedError Nếu truyền vào null, tức là test case này PHẢI chạy thành công.
     *                      Nếu truyền vào chuỗi, tức là test case này PHẢI quăng lỗi chứa chuỗi đó.
     */
    private static void testCase(int id, String rule, String action, Runnable test, String expectedError) {
        System.out.printf("TC%02d - Quy tắc  : %s\n", id, rule);
        System.out.printf("       Hành động : %s\n", action);
        try {
            test.run();
            if (expectedError != null) {
                System.out.println("       Kết quả   : ❌ THẤT BẠI (Chạy thành công trong khi đáng lẽ phải văng lỗi!)");
            } else {
                System.out.println("       Kết quả   : ✅ THÀNH CÔNG (Chạy hợp lệ đúng như kỳ vọng)");
            }
        } catch (Exception e) {
            if (expectedError != null && e.getMessage().contains(expectedError)) {
                System.out.printf("       Kết quả   : ✅ THÀNH CÔNG (Bắt đúng lỗi: \"%s\")\n", e.getMessage());
            } else {
                System.out.printf("       Kết quả   : ❌ THẤT BẠI (Văng lỗi không mong đợi: %s)\n", e.getMessage());
            }
        }
        System.out.println("-".repeat(70));
    }
}
