--
-- PostgreSQL database dump
--

\restrict 6QXfhCNcbdZo7Fey0nLZOToUQlQCe8MPzZY1clAczEsETxQORyqMc8NdpzXQJAp

-- Dumped from database version 15.14
-- Dumped by pg_dump version 15.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products VALUES (4, 'Arrow', 'Shirts', '2025-09-10 15:19:25.486751', 'Premium cotton formal shirt for office wear - Sky Blue', 'Size: L, Collar: 16 inches', true, false, 'Men''s Cotton Formal Shirt', 'Men''s Fashion', 1999.00, 4.2, 67, 'ARW-MFS01', 50, '2025-09-10 15:19:25.486784', 0, 0);
INSERT INTO public.products VALUES (5, 'Levi''s', 'Pants', '2025-09-10 15:19:35.426738', 'Dark wash slim fit jeans with stretch comfort', 'Size: 32W x 34L', true, true, 'Men''s Slim Fit Jeans', 'Men''s Fashion', 2499.00, 4.4, 89, 'LEV-MJ01', 40, '2025-09-10 15:19:35.426769', 1, 0);
INSERT INTO public.products VALUES (6, 'Zara', 'Dresses', '2025-09-10 15:19:42.60266', 'Light and breezy floral print midi dress perfect for summer', 'Size: M, Length: Midi', true, true, 'Women''s Floral Summer Dress', 'Women''s Fashion', 2999.00, 4.5, 156, 'ZAR-WSD01', 25, '2025-09-10 15:19:42.602693', 0, 0);
INSERT INTO public.products VALUES (7, 'Nike', 'Pants', '2025-09-10 15:19:50.870679', 'Comfortable high-waist leggings for yoga and casual wear', 'Size: M, High waist', true, false, 'Women''s High Waist Leggings', 'Women''s Fashion', 1299.00, 4.3, 234, 'NIK-WL01', 60, '2025-09-10 15:19:50.870709', 0, 0);
INSERT INTO public.products VALUES (8, 'H&M', 'Boys', '2025-09-10 15:19:58.703416', 'Soft cotton t-shirt with cartoon print for boys aged 5-10', 'Age: 5-10 years, Size: 8Y', true, false, 'Boys Cotton T-Shirt', 'Kids Fashion', 599.00, 4.1, 45, 'HM-BT01', 80, '2025-09-10 15:19:58.703448', 0, 0);
INSERT INTO public.products VALUES (9, 'Carter''s', 'Girls', '2025-09-10 15:20:05.785187', 'Beautiful pink princess dress for special occasions', 'Age: 3-8 years, Size: 6Y', true, true, 'Girls Princess Dress', 'Kids Fashion', 1599.00, 4.6, 78, 'CAR-GD01', 30, '2025-09-10 15:20:05.785217', 0, 0);
INSERT INTO public.products VALUES (10, 'Sony', 'Gaming Console', '2025-09-10 15:21:02.842661', 'Next-gen gaming console with 4K gaming and ray tracing', '390 x 104 x 260 mm', true, true, 'Sony PlayStation 5', 'Electronics', 49990.00, 4.7, 312, 'SON-PS5', 12, '2025-09-10 15:21:02.8427', 5, 0);
INSERT INTO public.products VALUES (12, 'US Polo', 'T-Shirts', '2025-09-10 15:21:16.319139', '100% cotton polo shirt available in multiple colors', 'Size: L, Regular Fit', true, false, 'Men''s Casual Polo T-Shirt', 'Men''s Fashion', 1299.00, 4.2, 145, 'USP-MPT01', 75, '2025-09-10 15:21:16.319162', 0, 0);
INSERT INTO public.products VALUES (13, 'Clarks', 'Shoes', '2025-09-10 15:21:23.914695', 'Genuine leather oxford shoes for formal occasions', 'Size: 9 UK, Oxford style', true, true, 'Men''s Leather Formal Shoes', 'Men''s Fashion', 3999.00, 4.6, 98, 'CLK-MFS01', 25, '2025-09-10 15:21:23.91472', 1, 0);
INSERT INTO public.products VALUES (14, 'North Face', 'Jackets', '2025-09-10 15:21:32.001895', 'Water-resistant winter jacket with thermal insulation', 'Size: L, Regular Fit', true, false, 'Men''s Winter Jacket', 'Men''s Fashion', 4999.00, 4.4, 67, 'NF-MWJ01', 20, '2025-09-10 15:21:32.001936', 1, 0);
INSERT INTO public.products VALUES (15, 'Van Heusen', 'Tops', '2025-09-10 15:21:38.393059', 'Elegant silk blouse perfect for office and formal events', 'Size: M, Regular Fit', true, true, 'Women''s Silk Blouse', 'Women''s Fashion', 2799.00, 4.3, 112, 'VH-WSB01', 35, '2025-09-10 15:21:38.39309', 0, 0);
INSERT INTO public.products VALUES (16, 'Steve Madden', 'Shoes', '2025-09-10 15:21:50.072646', 'Elegant 3-inch stiletto heels for formal occasions', 'Size: 7 UK, 3-inch heel', true, true, 'Women''s High Heels', 'Women''s Fashion', 3499.00, 4, 76, 'SM-WHH01', 18, '2025-09-10 15:21:50.07267', 1, 0);
INSERT INTO public.products VALUES (17, 'Adidas', 'Sports Equipment', '2025-09-10 15:22:01.278073', 'FIFA approved football for professional matches', 'Size: 5, Official dimensions', true, true, 'Football Official Size', 'Sports & Fitness', 1899.00, 4.6, 89, 'ADI-FB01', 25, '2025-09-10 15:22:01.278094', 0, 0);
INSERT INTO public.products VALUES (18, 'Nivea', 'Skincare', '2025-09-10 15:22:09.119561', 'Daily face moisturizer with sun protection for all skin types', '75ml tube', true, false, 'Face Moisturizer SPF 30', 'Beauty & Personal Care', 899.00, 4.2, 234, 'NIV-FM30', 85, '2025-09-10 15:22:09.119592', 0, 0);
INSERT INTO public.products VALUES (19, 'L''Oreal', 'Hair Care', '2025-09-10 15:22:16.635905', 'Professional hair gel for long-lasting hairstyles', '150ml bottle', true, false, 'Hair Styling Gel Strong Hold', 'Beauty & Personal Care', 599.00, 4.1, 167, 'LOR-HSG01', 95, '2025-09-10 15:22:16.635926', 0, 0);
INSERT INTO public.products VALUES (20, 'Bajaj', 'Kitchen Appliances', '2025-09-10 15:23:01.453881', 'Auto pop-up toaster with 7 browning controls and defrost function', '28 x 18 x 19 cm', true, false, 'Bajaj Majesty 2-Slice Pop-up Toaster', 'Home & Kitchen', 1899.00, 4.2, 189, 'BAJ-T2S', 45, '2025-09-10 15:23:01.453994', 2, 0);
INSERT INTO public.products VALUES (21, 'CraftVilla', 'Home Decor', '2025-09-10 15:23:08.152969', 'Handcrafted wooden wall clock with Roman numerals', '35 x 35 x 4 cm', true, true, 'Wooden Wall Clock Vintage', 'Home & Kitchen', 2499.00, 4.5, 67, 'CV-WWC01', 25, '2025-09-10 15:23:08.152997', 1, 0);
INSERT INTO public.products VALUES (22, 'Milton', 'Storage', '2025-09-10 15:23:16.889094', 'Stackable transparent storage containers with airtight lids', 'Set of 3 sizes', true, false, 'Plastic Storage Box Set of 3', 'Home & Kitchen', 899.00, 4.3, 234, 'MIL-SB3', 75, '2025-09-10 15:23:16.889125', 1, 0);
INSERT INTO public.products VALUES (23, 'Oral-B', 'Personal Care', '2025-09-10 15:23:24.413112', 'Rechargeable electric toothbrush with 2-minute timer', '24 x 3 cm', true, true, 'Electric Toothbrush with Timer', 'Health & Wellness', 2799.00, 4.6, 145, 'ORB-ET01', 35, '2025-09-10 15:23:24.413144', 0, 0);
INSERT INTO public.products VALUES (24, 'Boldfit', 'Fitness Equipment', '2025-09-10 15:23:30.787501', '5-piece resistance bands set for home workouts', 'Set of 5 bands', true, false, 'Resistance Bands Set', 'Health & Wellness', 1299.00, 4.4, 178, 'BF-RB5', 55, '2025-09-10 15:23:30.787528', 1, 0);
INSERT INTO public.products VALUES (25, 'Portronics', 'Car Accessories', '2025-09-10 15:23:37.392506', '360-degree rotating phone holder for car dashboard', '15 x 10 x 8 cm', true, false, 'Car Phone Holder Dashboard Mount', 'Automotive', 599.00, 4.1, 267, 'POR-PH01', 85, '2025-09-10 15:23:37.392532', 0, 0);
INSERT INTO public.products VALUES (26, 'Wavex', 'Car Care', '2025-09-10 15:23:43.705585', 'Premium car wax for shine and protection', '500ml bottle', true, false, 'Car Wax Polish 500ml', 'Automotive', 899.00, 4.3, 89, 'WAV-CW500', 65, '2025-09-10 15:23:43.705609', 1, 0);
INSERT INTO public.products VALUES (27, 'Trust', 'Gardening Tools', '2025-09-10 15:23:50.338894', 'Essential gardening tools with wooden handles', 'Tool set with carry bag', true, true, 'Garden Tool Set 5-Piece', 'Garden & Outdoor', 1699.00, 4.2, 123, 'TRU-GT5', 40, '2025-09-10 15:23:50.338918', 2, 0);
INSERT INTO public.products VALUES (28, 'Supreme', 'Outdoor Furniture', '2025-09-10 15:23:56.863418', 'Weather-resistant plastic chairs for outdoor use', 'Set of 4 chairs', true, false, 'Plastic Garden Chair Set of 4', 'Garden & Outdoor', 3999.00, 4, 56, 'SUP-GC4', 20, '2025-09-10 15:23:56.863445', 8, 0);
INSERT INTO public.products VALUES (29, 'Pedigree', 'Dog Supplies', '2025-09-10 15:24:04.023088', 'Non-slip stainless steel food bowl for medium dogs', '20 x 20 x 6 cm', true, false, 'Dog Food Bowl Stainless Steel', 'Pet Supplies', 699.00, 4.4, 156, 'PED-DFB01', 90, '2025-09-10 15:24:04.023113', 0, 0);
INSERT INTO public.products VALUES (30, 'Whiskas', 'Cat Supplies', '2025-09-10 15:24:11.002153', 'Multi-level scratching post with hanging toys', '40 x 40 x 80 cm', true, true, 'Cat Scratching Post with Toys', 'Pet Supplies', 2299.00, 4.6, 89, 'WHI-CSP01', 25, '2025-09-10 15:24:11.002171', 3, 0);
INSERT INTO public.products VALUES (31, 'Pampers', 'Baby Care', '2025-09-10 15:24:18.970613', 'Ultra-soft diaper pants with 12-hour protection', 'Size M, Pack of 64', true, true, 'Baby Diaper Pants Size M', 'Baby & Kids', 899.00, 4.7, 456, 'PAM-DPM', 120, '2025-09-10 15:24:18.970637', 1, 0);
INSERT INTO public.products VALUES (32, 'Fisher-Price', 'Toys', '2025-09-10 15:24:25.863056', 'Colorful building blocks for creativity and learning', 'Age 2-6 years', true, false, 'Educational Building Blocks', 'Baby & Kids', 1499.00, 4.5, 234, 'FP-EBB01', 65, '2025-09-10 15:24:25.86308', 2, 0);
INSERT INTO public.products VALUES (33, 'JK Copier', 'Office Supplies', '2025-09-10 15:24:32.86429', 'High-quality 75 GSM white paper for printing', '500 sheets ream', true, false, 'A4 Printer Paper Ream', 'Office & Stationery', 299.00, 4.1, 89, 'JK-A4R', 200, '2025-09-10 15:24:32.864316', 3, 0);
INSERT INTO public.products VALUES (34, 'Green Soul', 'Office Furniture', '2025-09-10 15:24:40.338973', 'Adjustable height office chair with lumbar support', '120 x 60 x 95 cm', true, true, 'Ergonomic Office Chair', 'Office & Stationery', 8999.00, 4.3, 145, 'GS-EOC01', 15, '2025-09-10 15:24:40.338999', 19, 0);
INSERT INTO public.products VALUES (2, 'Apple', 'Laptop', '2025-09-10 15:18:05.373514', 'Apple MacBook Pro with M3 chip, 16GB RAM, 512GB SSD', '312 x 221 x 15 mm', false, true, 'MacBook Pro 14-inch M3', 'Electronics', 199900.00, 4.8, 89, 'APL-MBP14', 15, '2025-09-10 16:00:34.09326', 2, 0);
INSERT INTO public.products VALUES (3, 'Apple', 'Tablet', '2025-09-10 15:18:13.045417', '10.9-inch iPad Air with M1 chip and Apple Pencil support', '248 x 178 x 6.1 mm', false, false, 'iPad Air 5th Gen', 'Electronics', 54900.00, 4.5, 156, 'APL-IPAD5', 30, '2025-09-10 16:00:34.10001', 0, 0);
INSERT INTO public.products VALUES (11, 'Apple', 'Smart Watch', '2025-09-10 15:21:09.659434', 'Advanced fitness tracker with health monitoring features', '45 x 38 x 10.7 mm', false, false, 'Apple Watch Series 9', 'Electronics', 41900.00, 4.5, 189, 'APL-AW9', 28, '2025-09-11 11:59:57.021609', 0, 0);
INSERT INTO public.products VALUES (1, 'Samsung', 'Smartphone', '2025-09-10 15:17:50.937553', 'Premium Android smartphone with S Pen and 200MP camera', '163 x 78 x 8.9 mm', false, true, 'Samsung Galaxy S23 Ultra', 'Electronics', 89999.00, 4.6, 128, 'SAM-S23U', 50, '2025-09-10 16:23:11.460899', 0, 1);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 34, true);


--
-- PostgreSQL database dump complete
--

\unrestrict 6QXfhCNcbdZo7Fey0nLZOToUQlQCe8MPzZY1clAczEsETxQORyqMc8NdpzXQJAp

