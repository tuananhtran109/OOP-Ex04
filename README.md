# Hệ thống Tính Lương và Thưởng Nhân Sự (OOP Lab 4)

## Giới thiệu
Đây là bài tập thực hành Lập trình Hướng đối tượng tuần 4, triển khai bằng ngôn ngữ Java. 

Hệ thống được xây dựng nhằm giải quyết bài toán tự động hóa việc quản lý và tính toán thu nhập hàng tháng cho nhiều loại nhân sự khác nhau trong một doanh nghiệp, bao gồm:
- **Nhân viên nhận lương cố định** (`SalariedEmployee`)
- **Nhân viên làm theo giờ** (`HourlyEmployee`)
- **Nhân viên kinh doanh** (`SalesEmployee`)

Dự án áp dụng triệt để các tính chất cốt lõi của OOP như **Kế thừa (Inheritance)**, **Đa hình (Polymorphism)**, **Ghi đè (Overriding)** và **Nạp chồng (Overloading)**.

## Tác giả
- **Họ tên:** Trần Tuấn Anh
- **Mã số sinh viên:** 202418834

## Báo cáo chi tiết
Vui lòng xem file **`Report.pdf`** đính kèm trong kho lưu trữ này để xem toàn bộ thông tin về:
- Phân tích yêu cầu nghiệp vụ
- Sơ đồ lớp (UML Class Diagram)
- Phân tích các quyết định thiết kế
- Hơn 10 kịch bản kiểm thử (Test Cases) chi tiết bao gồm dữ liệu hợp lệ, kiểm thử biên và kiểm thử bắt lỗi.

## Hướng dẫn cài đặt và chạy thử
1. Mở Terminal/Command Prompt tại thư mục chứa dự án.
2. Biên dịch toàn bộ mã nguồn Java:
   ```bash
   javac *.java
   ```
3. Chạy chương trình chính (Thêm cấu hình UTF-8 để hiển thị tiếng Việt không bị lỗi):
   ```bash
   java "-Dfile.encoding=UTF-8" Main
   ```
