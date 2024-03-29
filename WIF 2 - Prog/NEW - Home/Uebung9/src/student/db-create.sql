USE [Datenbank]
GO
/****** Object:  Table [dbo].[Studenten]    Script Date: 23.11.2020 15:47:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Studenten](
	[MartNr] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Semester] [int] NOT NULL,
	[ECTS] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MartNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Studenten]  WITH CHECK ADD  CONSTRAINT [CK__Studenten__MartN__3B75D760] CHECK  (([MartNr]>=(100000)))
GO
ALTER TABLE [dbo].[Studenten] CHECK CONSTRAINT [CK__Studenten__MartN__3B75D760]
GO
ALTER TABLE [dbo].[Studenten]  WITH CHECK ADD  CONSTRAINT [CK__Studenten__MartN__3C69FB99] CHECK  (([MartNr]<=(999999)))
GO
ALTER TABLE [dbo].[Studenten] CHECK CONSTRAINT [CK__Studenten__MartN__3C69FB99]
GO
ALTER TABLE [dbo].[Studenten]  WITH CHECK ADD  CONSTRAINT [ECTS1] CHECK  (([ECTS]>=(0)))
GO
ALTER TABLE [dbo].[Studenten] CHECK CONSTRAINT [ECTS1]
GO
ALTER TABLE [dbo].[Studenten]  WITH CHECK ADD  CONSTRAINT [ECTS2] CHECK  (([ECTS]<=(210)))
GO
ALTER TABLE [dbo].[Studenten] CHECK CONSTRAINT [ECTS2]
GO
ALTER TABLE [dbo].[Studenten]  WITH CHECK ADD  CONSTRAINT [Sem1] CHECK  (([Semester]>(0)))
GO
ALTER TABLE [dbo].[Studenten] CHECK CONSTRAINT [Sem1]
GO
ALTER TABLE [dbo].[Studenten]  WITH CHECK ADD  CONSTRAINT [Sem2] CHECK  (([Semester]<=(9)))
GO
ALTER TABLE [dbo].[Studenten] CHECK CONSTRAINT [Sem2]
GO
