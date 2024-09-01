CREATE DATABASE Instrumentos;

use Instrumentos;

create table TipoInstrumento (
       codigo  varchar(10)  not null,
       nombre varchar(30) not null,
	   unidad  varchar(20),
       Primary Key (codigo)         
);

create table Instrumento (
       tipoisntrumentos varchar(10) not null,
       serie  varchar(10)  not null,
       descripcion varchar(30) not null,
	   minimo  varchar(20),
	   maximo varchar(20),
	   tolerancia  varchar(20),
       Primary Key (serie)         
);

create table Calibraciones (
       numero int not null auto_increment,
       fecha varchar(10)  not null,
       mediciones int not null,
	   instrumento varchar(10) not null,
       Primary Key (numero)         
);

create table Mediciones (
       calibracion int not null,
       medida  int not null auto_increment,
       referencia int not null,
	   lectura int not null,
       Primary Key (medida)         
);

ALTER TABLE Mediciones ADD Foreign Key(calibracion) REFERENCES Calibraciones(numero);
ALTER TABLE Calibraciones ADD Foreign Key(instrumento) REFERENCES Instrumento(serie);
ALTER TABLE Instrumento ADD Foreign Key(tipoisntrumentos) REFERENCES TipoInstrumento(codigo);

insert into TipoInstrumento (codigo ,nombre, unidad) values('TER','Termómetro','Grados Celcius');
insert into Instrumento (tipoisntrumentos ,serie, descripcion,minimo,maximo,tolerancia) values('TER','001','Termometro Hahs','10','100','1');
insert into Calibraciones (numero ,fecha, mediciones,instrumento) values(01,'22/10/23',2,'001');
insert into Mediciones (calibracion ,medida, referencia,lectura) values(01,01,10,95);
insert into Mediciones (calibracion ,medida, referencia,lectura) values(01,02,20,98);
select * from Calibraciones n inner join Instrumento i on n.instrumento = i.serie;
select * from Mediciones n inner join Calibraciones i on n.calibracion = i.numero;

delete from Mediciones where calibracion= 2;