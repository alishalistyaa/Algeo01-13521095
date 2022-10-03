# Algeo01-21095 - ㅡ♡Xx_Bwasr3ngL0vers69_xX♡ㅡ
<!-- ## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)
* [License](#license) -->

## General Information
A matrix calculator made in Java. This repository is an archive of the files for Tugas Besar 1 IF2123 Aljabar Linear Geometri 2022/2023.
The main goal of the project is to make a Java library that is able to solve Linear Algebra problems with several methods including matrices. These methods include Gauss Elimination, Gauss-Jordan Elimintaion, Determinant, Inverse, and Cramer's Rule. Aside of that, this proejct also include several implementation of the library: Polinomial Interpolation, Bicubic Interpolation, and Double Linear Regression.
Contributors:
- 13520095 Muhamad Aji Wibisono
- 13520129 Chiquita Ahsanunnisa
- 13520171 Alisha Listya Wardhani

## Technologies Used
- Java 


## Features
- Solving Linear Systems of equations using Gauss Elimination Method, Gauss-Jordan Elimination Method, Inverse Method, and Cramer's Rule
- Solving determinants using Cofactor and Inverse matrix methods
- Solving the inverse of a Matrix using Gauss elimination and Adjoint matrix method
- Solving polinomial interpolation problems
- Solving bicubic interpolation problems
- Solving double regression problems
- Image Upscaling using bicubic interpolation


## Structure

```
├── README.md
├── bin
│   ├── BicubicInterpolation.class
│   ├── ImageUpsc.class
│   ├── ImageUtil.class
│   ├── InterpolasiPolinom.class
│   ├── Main.class
│   ├── matriks.class
│   ├── operasiMatriks.class
│   ├── RegresiLinierBerganda.class
│   └── SPL.class
│       
├── doc
│   └── Algeo01-21095.pdf
├── lib
│   └── Algeo01-21095.jar
├── src
│   ├── BicubicInterpolation.java
│   ├── ImageUpsc.java
│   ├── ImageUtil.java
│   ├── InterpolasiPolinom.java
│   ├── Main.java
│   ├── matriks.java
│   ├── operasiMatriks.java
│   ├── RegresiLinierBerganda.java
│   └── SPL.java
└── test
    ├── 1_A.txt
    ├── 1_B.txt
    ├── 1_D.txt
    ├── 1_D_1.txt
    ├── 1_D_2.txt
    ├── 2_A.txt
    ├── 2_B.txt
    ├── 3_A.txt
    ├── 3_B.txt
    ├── 4_A_1.txt
    ├── 4_A_2.txt
    ├── 4_A_3.txt
    ├── 4_A_4.txt
    ├── 4_B_1.txt
    ├── 4_B_2.txt
    ├── 4_B_3.txt
    ├── 4_B_4.txt
    ├── 4_C_1.txt
    ├── 4_C_2.txt
    ├── 4_C_3.txt
    ├── 5_a.txt
    ├── 5_b.txt
    ├── 5_c.txt
    ├── 5_d.txt
    ├── 6.txt
    ├── ImageScaling_0.jpg
    ├── ImageScaling_0.jpg_upscaled2x.png
    ├── ImageScaling_1.png
    ├── ImageScaling_1.png_upscaled2x.png
    ├── ImageScaling_2.png
    ├── ImageScaling_2.png_upscaled2x.png
    ├── ImageScaling_BosLevel.png
    ├── ImageScaling_BosLevel.png_upscaled2x.png
    ├── jawaban_1_A_cramer.txt
    ├── jawaban_1_A_gauss.txt
    ├── jawaban_1_A_gaussjordan.txt
    ├── jawaban_1_A_inverse.txt
    ├── jawaban_1_B_cramer.txt
    ├── jawaban_1_B_gauss.txt
    ├── jawaban_1_B_gaussjordan.txt
    ├── jawaban_1_B_inverse.txt
    ├── jawaban_1_C_cramer.txt
    ├── jawaban_1_C_gauss.txt
    ├── jawaban_1_C_gaussjordan.txt
    ├── jawaban_1_C_inverse.txt
    ├── jawaban_1_D_1_cramer.txt
    ├── jawaban_1_D_1_gauss.txt
    ├── jawaban_1_D_1_gaussjordan.txt
    ├── jawaban_1_D_1_inverse.txt
    ├── jawaban_1_D_2_cramer.txt
    ├── jawaban_1_D_2_gauss.txt
    ├── jawaban_1_D_2_gaussjordan.txt
    ├── jawaban_1_D_2_inverse.txt
    ├── jawaban_2_A_cramer.txt
    ├── jawaban_2_A_gauss.txt
    ├── jawaban_2_A_gaussjordan.txt
    ├── jawaban_2_A_inverse.txt
    ├── jawaban_2_B_cramer.txt
    ├── jawaban_2_B_gauss.txt
    ├── jawaban_2_B_gaussjordan.txt
    ├── jawaban_2_B_inverse.txt
    ├── jawaban_3_A_cramer.txt
    ├── jawaban_3_A_gauss.txt
    ├── jawaban_3_A_gaussjordan.txt
    ├── jawaban_3_A_inverse.txt
    ├── jawaban_3_B_cramer.txt
    ├── jawaban_3_B_gauss.txt
    ├── jawaban_3_B_gaussjordan.txt
    ├── jawaban_3_B_inverse.txt
    ├── jawaban_4_A_1.txt
    ├── jawaban_4_A_2.txt
    ├── jawaban_4_A_3.txt
    ├── jawaban_4_A_4.txt
    ├── jawaban_4_B_1.txt
    ├── jawaban_4_B_2.txt
    ├── jawaban_4_B_3.txt
    ├── jawaban_4_B_4.txt
    ├── jawaban_4_C_1.txt
    ├── jawaban_4_C_2.txt
    ├── jawaban_4_C_3.txt
    ├── jawaban_5_A.txt
    ├── jawaban_5_B.txt
    ├── jawaban_5_C.txt
    └── jawaban_6.txt
    
```

---

## How to Use

### Dependencies
- Java Virtual Environment
- Java Development Kit

### Installation
- Download and install [Java](https://www.java.com/en/download/)
- Download and install [Java Development Kit](https://www.oracle.com/java/technologies/downloads/)
- Download all forlder and files on this repository or simply clone this repo!

### Program Execution
    git clone https://github.com/alishalistyaa/Algeo01-21095
    cd Algeo01-20083/bin
    java Main
This repo also includes a jar file that you can set up and run more practically!


##Brought to you by 
world's biggest basreng fans x_X
![image](https://user-images.githubusercontent.com/73476678/193554021-50e4a709-9140-4676-99b4-e5b1f6588499.png)


 ㅡ♡Xx_Bwasr3ngL0vers69_xX♡ㅡ
 2022
 *featuring pika pika*
 ![image](https://user-images.githubusercontent.com/73476678/193554104-e5aaa721-6785-4b6a-b39f-f6ae0d835416.png)
