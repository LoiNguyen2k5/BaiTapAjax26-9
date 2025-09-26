Spring Boot GraphQL CRUD API Demo
Dự án này là một ví dụ minh họa về cách xây dựng một API CRUD (Create, Read, Update, Delete) hoàn chỉnh sử dụng Spring Boot 3 và GraphQL. Ứng dụng quản lý ba đối tượng chính: User, Category, và Product với các mối quan hệ được định nghĩa bằng JPA/Hibernate.


Giao diện người dùng (frontend) được xây dựng bằng HTML và JavaScript thuần (sử dụng fetch API) để tương tác với GraphQL backend.

✨ Tính năng chính
API GraphQL: Cung cấp một endpoint duy nhất (/graphql) để thực hiện tất cả các thao tác dữ liệu.

CRUD đầy đủ: Hỗ trợ các thao tác tạo, đọc, cập nhật, xóa cho cả ba đối tượng User, Category, và Product.

Truy vấn nâng cao:

Lấy danh sách sản phẩm được sắp xếp theo giá từ thấp đến cao.

Lọc và lấy danh sách sản phẩm theo một Category cụ thể.

Quan hệ dữ liệu:

User - Product: Một-Nhiều (Một User có thể tạo nhiều Product).

Category - Product: Một-Nhiều (Một Category có thể chứa nhiều Product).

Cơ sở dữ liệu In-Memory: Sử dụng H2 Database để dễ dàng khởi chạy và thử nghiệm mà không cần cài đặt.

Giao diện test API: Tích hợp sẵn giao diện GraphiQL để dễ dàng kiểm tra các query và mutation.

Frontend Demo: Một trang index.html đơn giản để minh họa cách gọi và sử dụng API từ client.

🚀 Công nghệ sử dụng
Backend:

Java 17

Spring Boot 3.1.5

Spring for GraphQL

Spring Data JPA (Hibernate)

Maven

Database:

H2 In-Memory Database

Frontend:

HTML5

CSS3

JavaScript (ES6+ Fetch API)

Giao diện CRUD Demo:

http://localhost:8080