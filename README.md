# Triangle — Lab 4 (Kiểm thử & Coverage)

Chương trình Java phân loại tam giác theo ba cạnh `a`, `b`, `c`, kèm unit test (JUnit 5) và đo coverage bằng JaCoCo.

## Yêu cầu

- **JDK 17** trở lên
- **Apache Maven 3.6+**

Kiểm tra:

```powershell
java -version
mvn -version
```

## Cấu trúc project

```
lab3/
├── pom.xml
├── src/main/java/Triangle.java      # Phương thức classify()
└── src/test/java/TriangleTest.java  # Unit test
```

## Chạy test

Mở terminal tại thư mục project:

```powershell
cd c:\projects\lab3
mvn test
```

Kết quả mong đợi: **11 test** (5 basis path + 6 bổ sung), tất cả PASS.

## Báo cáo coverage (JaCoCo)

```powershell
mvn clean test jacoco:report
```

Mở file trong trình duyệt:

```
target/site/jacoco/index.html
```

Chọn **Triangle** → **Triangle.java** để xem chi tiết từng dòng/nhánh.

## Phương thức `classify`

| Kết quả | Điều kiện |
|---------|-----------|
| `Invalid` | Có cạnh ≤ 0 |
| `Not a triangle` | Không thỏa bất đẳng thức tam giác |
| `Equilateral` | Ba cạnh bằng nhau |
| `Isosceles` | Hai cạnh bằng nhau |
| `Scalene` | Ba cạnh khác nhau |

## Bộ test case

### Basis path (P1–P5) — Cyclomatic Complexity = 5

| Path | (a, b, c) | Kết quả | Luồng điều khiển |
|------|-----------|---------|------------------|
| P1 | (0, 1, 1) | Invalid | N1(T) |
| P2 | (1, 2, 5) | Not a triangle | N1(F) → N3(T) |
| P3 | (3, 3, 3) | Equilateral | N1(F) → N3(F) → N5(T) |
| P4 | (3, 3, 4) | Isosceles | N1(F) → N3(F) → N5(F) → N7(T) |
| P5 | (3, 4, 5) | Scalene | N1(F) → N3(F) → N5(F) → N7(F) |

### Bổ sung (branch coverage JaCoCo)

| (a, b, c) | Kết quả | Mục đích |
|-----------|---------|----------|
| (1, 0, 1) | Invalid | Nhánh `b <= 0` |
| (1, 1, 0) | Invalid | Nhánh `c <= 0` |
| (1, 10, 2) | Not a triangle | Nhánh `a + c <= b` |
| (10, 1, 2) | Not a triangle | Nhánh `b + c <= a` |
| (4, 5, 5) | Isosceles | Nhánh `b == c` |
| (5, 4, 5) | Isosceles | Nhánh `a == c` |

## Kết quả coverage

| Bộ test | Statement (`classify`) | Branch (`classify`) |
|---------|------------------------|---------------------|
| Chỉ 5 test basis path | **100%** | **72%** (16/22) |
| 5 + 6 test bổ sung | **100%** | **100%** (22/22) |

**Giải thích ngắn gọn:** Bộ 5 test đủ phủ mọi câu lệnh và mọi nhánh `if` cấp cao, nhưng JaCoCo đếm thêm từng nhánh con trong biểu thức `&&` / `||` (short-circuit). Cần thêm 6 test để đạt 100% branch coverage.

## Clone project

```powershell
git clone https://github.com/TranTu7425/lab3.git
cd lab3
mvn test
```

## Công nghệ

- Java 17
- JUnit 5
- Maven Surefire
- JaCoCo 0.8.12
