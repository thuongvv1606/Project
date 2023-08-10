USE [master]
GO

/*******************************************************************************
   Drop database if it exists
********************************************************************************/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'HighLand')
BEGIN
	ALTER DATABASE HighLand SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE HighLand SET ONLINE;
	DROP DATABASE HighLand;
END

GO

CREATE DATABASE HighLand
GO

USE HighLand
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/****** Object:  Table [dbo].[PackageTypes]    Script Date: 24/10/2022 23:03:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Staff](
	[account] [nchar](10) NOT NULL,
	[Password] [nchar](10) NULL,
	[name] [nvarchar](50) NULL,
	[phone] [char](10),
	[gender] [bit] NULL,
	[address] [nvarchar](50) NULL,
	[dateofbirth] [date] NULL,
 CONSTRAINT [PK_Staff] PRIMARY KEY CLUSTERED 
(
	[account] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL primary key,
	[CategoryName] [nvarchar](50) NULL
)
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [varchar](5) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[UnitPrice] [money] NULL,
	[UnitsInStock] [int] NULL,
	[Image] [nvarchar](max),
	[CategoryID] [int] NULL,
	[Discontinued] [bit] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [nvarchar](50) NULL,
	[phone] [char](10) NULL,
	[Gender] [bit] NULL,
	[Address] [nvarchar](50) NULL,
	[Birthdate] [datetime] NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [varchar](10) NOT NULL,
	[OrderDate] [datetime] NOT NULL
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItems](
	[OrderItemID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [numeric](18, 0) NOT NULL,
	[ProductID] [varchar](5) NOT NULL,
	[Quantity] [int] NOT NULL,
 CONSTRAINT [PK_OrderItems] PRIMARY KEY CLUSTERED 
(
	[OrderItemID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (1, N'Cafe Pha Phin')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (2, N'Trà')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (3, N'Freeze')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (4, N'Phindi')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (5, N'Cafe Espresso')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (6, N'Other Drinks')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (7, N'Bánh')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (8, N'Bánh Mì Que')
INSERT [dbo].[Categories] ([CategoryID], [CategoryName]) VALUES (9, N'Topping')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
 

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP001', N'PHIN SỮA ĐÁ S', 29.000, 100,N'PhinSuaDa.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP002', N'PHIN SỮA ĐÁ M', 35.000, 100,N'PhinSuaDa.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP003', N'PHIN SỮA ĐÁ L', 39.000, 100,N'PhinSuaDa.jpg', 1, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP004', N'PHIN SỮA NÓNG S', 29.000, 100,N'PhinSuaNong.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP005', N'PHIN SỮA NÓNG M', 35.000, 100,N'PhinSuaNong.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP006', N'PHIN SỮA NÓNG L', 39.000, 100,N'PhinSuaNong.jpg', 1, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP007', N'PHIN ĐEN ĐÁ S', 29.000, 100,N'PhinDenDa.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP008', N'PHIN ĐEN ĐÁ M', 35.000, 100,N'PhinDenDa.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP009', N'PHIN ĐEN ĐÁ L', 39.000, 100,N'PhinDenDa.jpg', 1, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP010', N'PHIN ĐEN Nóng S', 29.000, 100,N'PhinSuaNong.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP011', N'PHIN ĐEN Nóng M', 35.000, 100,N'PhinSuaNong.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP012', N'PHIN ĐEN Nóng L', 39.000, 100,N'PhinSuaNong.jpg', 1, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP013', N'BẠC XỈU ĐÁ S', 29.000, 100,N'BacXiuDa.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP014', N'BẠC XỈU ĐÁ M', 35.000, 100,N'BacXiuDa.jpg', 1, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CP015', N'BẠC XỈU ĐÁ L', 39.000, 100,N'BacXiuDa.jpg', 1, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT001', N'TRÀ SEN VÀNG S', 39.000, 200,N'TraSenVang.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT002', N'TRÀ SEN VÀNG M', 49.000, 200,N'TraSenVang.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT003', N'TRÀ SEN VÀNG L', 55.000, 1200,N'TraSenVang.jpg', 2, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT004', N'TRÀ THACH ĐÀO S', 39.000, 200,N'TraThachDao.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT005', N'TRÀ THACH ĐÀO M', 49.000, 200,N'TraThachDao.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT006', N'TRÀ THACH ĐÀO L', 55.000, 200,N'TraThachDao.jpg', 2, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT007', N'TRÀ THANH ĐÀO S', 39.000, 200,N'TraThanhDao.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT008', N'TRÀ THANH ĐÀO M', 49.000, 200,N'TraThanhDao.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT009', N'TRÀ THANH ĐÀO L', 55.000, 200,N'TraThanhDao.jpg', 2, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT010', N'TRÀ THACH VẢI S', 39.000, 200,N'TraThachVai.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT011', N'TRÀ THACH VẢI M', 49.000, 200,N'TraThachVai.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT012', N'TRÀ THACH VẢI L', 55.000, 200,N'TraThachVai.jpg', 2, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT013', N'TRÀ XANH ĐẬU ĐỎ S', 39.000, 200,N'TraXanhDauDo.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT014', N'TRÀ XANH ĐẬU ĐỎ M', 49.000, 200,N'TraXanhDauDo.jpg', 2, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('TT015', N'TRÀ XANH ĐẬU ĐỎ L', 55.000, 200,N'TraXanhDauDo.jpg', 2, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ001', N'FREEZE TRÀ XANH S', 49.000, 300,N'FreezeTraXanh.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ002', N'FREEZE TRÀ XANH M', 59.000, 300,N'FreezeTraXanh.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ003', N'FREEZE TRÀ XANH L', 65.000, 300,N'FreezeTraXanh.jpg', 3, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ004', N'FREEZE SÔ-CÔ-LA S', 49.000, 300,N'FreezeSoCoLa.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ005', N'FREEZE SÔ-CÔ-LA M', 59.000, 300,N'FreezeSoCoLa.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ006', N'FREEZE SÔ-CÔ-LA L', 65.000, 300,N'FreezeSoCoLa.jpg', 3, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ007', N'COOKIES&CREAM S', 49.000, 300,N'CookiesCream.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ008', N'COOKIES&CREAM M', 59.000, 300,N'CookiesCream.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ009', N'COOKIES&CREAM L', 65.000, 300,N'CookiesCream.jpg', 3, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ010', N'FREEZE SÔ-CÔ-LA S', 49.000, 300,N'FreezeSoCoLa.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ011', N'FREEZE SÔ-CÔ-LA M', 59.000, 300,N'FreezeSoCoLa.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ012', N'FREEZE SÔ-CÔ-LA L', 65.000, 300,N'FreezeSoCoLa.jpg', 3, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ013', N'CARAMEL PHIN FREEZE S', 49.000, 300,N'CaramelFreeze.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ014', N'CARAMEL PHIN FREEZE M', 59.000, 300,N'CaramelFreeze.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ015', N'CARAMEL PHIN FREEZE L', 65.000, 300,N'CaramelFreeze.jpg', 3, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ016', N'CLASSIC PHIN FREEZE S', 49.000, 300,N'ClassicPhinFreeze.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ017', N'CLASSIC PHIN FREEZE M', 59.000, 300,N'ClassicPhinFreeze.jpg', 3, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('FZ018', N'CLASSIC PHIN FREEZE L', 65.000, 300,N'ClassicPhinFreeze.jpg', 3, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD001', N'PhinDi Kem Sữa S', 39.000, 400,N'PhindiKemSua.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD002', N'PhinDi Kem Sữa M', 45.000, 400,N'PhindiKemSua.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD003', N'PhinDi Kem Sữa L', 49.000, 400,N'PhindiKemSua.jpg', 4, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD004', N'PhinDi Hạnh Nhân S', 39.000, 400,N'PhindiHanhNhan.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD005', N'PhinDi Hạnh Nhân M', 45.000, 400,N'PhindiHanhNhan.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD006', N'PhinDi Hạnh Nhân L', 49.000, 400,N'PhindiHanhNhan.jpg', 4, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD007', N'PhinDi Choco S', 39.000, 400,N'PhindiChoco.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD008', N'PhinDi Choco M', 45.000, 400,N'PhindiChoco.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD009', N'PhinDi Choco L', 49.000, 400,N'PhindiChoco.jpg', 4, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD010', N'PhinDi Hồng Trà S', 39.000, 400,N'PhinDiHongTra.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD011', N'PhinDi Hồng Trà M', 45.000, 400,N'PhinDiHongTra.jpg', 4, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PD012', N'PhinDi Hồng Trà L', 49.000, 400,N'PhinDiHongTra.jpg', 4, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE001', N'Espresso S', 35.000, 500,N'Espresso.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE002', N'Espresso M', 39.000, 500,N'Espresso.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE003', N'Espresso L', 45.000, 500,N'Espresso.jpg', 5, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE004', N'Cappuccino S', 55.000, 500,N'Cappuccino.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE005', N'Cappuccino M', 65.000, 500,N'Cappuccino.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE006', N'Cappuccino L', 69.000, 500,N'Cappuccino.jpg', 5, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE007', N'Mocha S', 59.000, 500,N'Mocha.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE008', N'Mocha M', 69.000, 500,N'Mocha.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE009', N'Mocha L', 75.000, 500,N'Mocha.jpg', 5, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE011', N'Americano S', 35.000, 500,N'Americano.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE012', N'Americano M', 39.000, 500,N'Americano.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE013', N'Americano L', 45.000, 500,N'Americano.jpg', 5, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE014', N'Latte S', 55.000, 500,N'latte.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE015', N'Latte M', 65.000, 500,N'latte.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE016', N'Latte L', 69.000, 500,N'latte.jpg', 5, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE017', N'Caramel Maccchiato S', 59.000, 500,N'Caramel.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE018', N'Caramel Maccchiato  M', 69.000, 500,N'Caramel.jpg', 5, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('CE019', N'Caramel Maccchiato  L', 75.000, 500,N'Caramel.jpg', 5, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD001', N'Chanh Đá Xay S', 39.000, 600,N'Caramel.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD002', N'Chanh Đá Xay M', 49.000, 600,N'Caramel.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD003', N'Chanh Đá Xay L', 55.000, 600,N'Caramel.jpg', 6, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD004', N'Chanh Đá Viên S', 39.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD005', N'Chanh Đá Viên M', 49.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD006', N'Chanh Đá Viên L', 55.000, 600,N'logo.jpg', 6, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD007', N'Chanh Dây Đá Viên S', 39.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD008', N'Chanh Dây Đá Viên M', 49.000, 600,N'logo.jpg',6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD009', N'Chanh Dây Đá Viên L', 55.000, 600,N'logo.jpg', 6, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD010', N'Tắc/Quất Đá Viên S', 39.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD011', N'Tắc/Quất Đá Viên M', 49.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD012', N'Tắc/Quất Đá Viên L', 55.000, 600,N'logo.jpg', 6, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD013', N'Sô-Cô-La(Nóng) S', 55.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD014', N'Sô-Cô-La(Nóng) M', 59.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD015', N'Sô-Cô-La(Nóng) L', 65.000, 600,N'logo.jpg', 6, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD016', N'Sô-Cô-La(Lạnh) S', 55.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD017', N'Sô-Cô-La(Lạnh) M', 59.000, 600,N'logo.jpg', 6, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('OD018', N'Sô-Cô-La(Lạnh) L', 65.000, 600,N'logo.jpg', 6, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP001', N'Tiramisu', 29.000, 700,N'Tiramisu.jpg', 7, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP002', N'Bánh Chuối', 29.000, 700,N'BanhChuoi.jpg', 7, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP003', N'Mousse Đào', 29.000, 700,N'MousseDao.jpg', 7, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP004', N'Phô Mai Trà Xanh', 29.000, 700,N'PhoMaiTraXanh.jpg', 7, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP005', N'Phô Mai Chanh Dây', 29.000, 700,N'PhoMaiChanhDay.jpg', 7, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP006', N'Phô Mai Cà Phê', 29.000, 700,N'PhoMaiCaPhe.jpg', 7, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP007', N'Phô Mai Caramel', 29.000, 700,N'CaramelPhoMai.jpg', 7, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP008', N'Sô-Cô-La Highlands', 29.000, 700,N'SoCoLaHL.jpg', 7, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('BP009', N'Sô-Cô-La', 29.000, 700,N'SoCoLaHL.jpg', 7, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('MQ001', N'GÀ Phô Mai', 19.000, 800,N'BanhMiQue.jpg', 8, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('MQ002', N'Pate bơ chà bông', 19.000, 800,N'BanhMiQue.jpg', 8, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT001', N'Thạch Cà Phê', 9.000, 700,N'logo.jpg', 9, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT002', N'Thạch Trà Xanh', 9.000, 700,N'logo.jpg', 9, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT003', N'Hạt Sen', 9.000, 700,N'logo.jpg', 9, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT004', N'Củ Năng', 9.000, 700,N'logo.jpg', 9, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT005', N'Thạch Đào', 9.000, 700,N'logo.jpg', 9, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT006', N'Đào Miếng', 9.000, 700,N'logo.jpg', 9, 1)

INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT007', N'Thạch Vải', 9.000, 700,N'logo.jpg', 9, 1)
INSERT [dbo].[Products] ([ProductID], [ProductName], [UnitPrice], [UnitsInStock],[Image], [CategoryID], [Discontinued]) VALUES ('PT008', N'Vải', 9.000, 700,N'logo.jpg', 9, 1)

GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
GO

GO
ALTER TABLE [dbo].[OrderItems]  WITH CHECK ADD  CONSTRAINT [FK_Products_OrderItems] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[OrderItems] CHECK CONSTRAINT [FK_Products_OrderItems]
GO


SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [CustomerId], [OrderDate]) VALUES (1, 1, '2022-01-01')
INSERT [dbo].[Orders] ([OrderID], [CustomerId], [OrderDate]) VALUES (2, 1, '2022-05-05')
INSERT [dbo].[Orders] ([OrderID], [CustomerId], [OrderDate]) VALUES (3, 2, '2022-07-07')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO

SET IDENTITY_INSERT [dbo].[OrderItems] ON 

INSERT [dbo].[OrderItems] ([OrderItemID],[OrderID], [ProductID], [Quantity]) VALUES (1,1, 'FZ001', 3)
INSERT [dbo].[OrderItems] ([OrderItemID],[OrderID], [ProductID], [Quantity]) VALUES (2,1, 'FZ002', 12)
INSERT [dbo].[OrderItems] ([OrderItemID],[OrderID], [ProductID], [Quantity]) VALUES (3,1, 'FZ003', 1)
INSERT [dbo].[OrderItems] ([OrderItemID],[OrderID], [ProductID], [Quantity]) VALUES (4,2, 'FZ004', 31)
INSERT [dbo].[OrderItems] ([OrderItemID],[OrderID], [ProductID], [Quantity]) VALUES (5,2, 'FZ005', 1)
INSERT [dbo].[OrderItems] ([OrderItemID],[OrderID], [ProductID], [Quantity]) VALUES (6,3, 'FZ006', 3)
SET IDENTITY_INSERT [dbo].[OrderItems] OFF
GO

SET IDENTITY_INSERT [dbo].[Customers] ON 
INSERT [dbo].[Customers] ([CustomerId], [CustomerName], [Phone], [Birthdate], [Gender], [Address]) VALUES (1, N'Nguyen Van A','0888160699', '1986-01-01', 1, N'Gia Lâm, Hà Nội')
INSERT [dbo].[Customers] ([CustomerId], [CustomerName], [Phone], [Birthdate], [Gender], [Address]) VALUES (2, N'Nguyen Van B','0888160699', '1990-06-03', 1, N'Lương Sơn, Hòa Bình')
INSERT [dbo].[Customers] ([CustomerId], [CustomerName], [Phone], [Birthdate], [Gender], [Address]) VALUES (3, N'Nguyen Thi C','0888160699', '1985-11-10', 0, N'Sapa, Lào Cai')
INSERT [dbo].[Customers] ([CustomerId], [CustomerName], [Phone], [Birthdate], [Gender], [Address]) VALUES (4, N'Nguyen Van D','0888160699', '2000-01-09', 1, N'Thạch Thất, Hà Nội')
INSERT [dbo].[Customers] ([CustomerId], [CustomerName], [Phone], [Birthdate], [Gender], [Address]) VALUES (5, N'Nguyen Thi E','0888160699', '1996-11-01', 0, N'Lý Nhân, Hà Nam')
SET IDENTITY_INSERT [dbo].[Customers] OFF
GO

INSERT [dbo].[Staff] ([Account], [Password], [Name], [Phone], [Gender], [Address]) VALUES ('admin', '123',N'Nguyen Van A','0888160699', 1, N'Gia Lâm, Hà Nội')
INSERT [dbo].[Staff] ([Account], [Password], [Name], [Phone], [Gender], [Address]) VALUES ('thuong', '123',N'Vu Van Thuong','0888160699', 1, N'Gia Lâm, Hà Nội')
GO


