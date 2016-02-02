/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes.FTC_example;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p>
 *Enables control of the robot via the gamepad
 */
public class acc_test extends OpMode {

    int   MPU6050_SMPLRT_DIV = 0x19;   // R/W
    int   MPU6050_CONFIG     =    0x1A;   // R/W
    int   MPU6050_GYRO_CONFIG = 0x1B;   // R/W
    int   MPU6050_ACCEL_CONFIG=0x1C;   // R/W
    int   MPU6050_FF_THR=0x1D;   // R/W
    int   MPU6050_FF_DUR=0x1E;   // R/W
    int   MPU6050_MOT_THR =0x1F;   // R/W
    int   MPU6050_MOT_DUR=0x20;   // R/W
    int   MPU6050_ZRMOT_THR=0x21;   // R/W
    int   MPU6050_ZRMOT_DUR= 0x22;   // R/W

    int   MPU6050_I2C_MST_CTRL=0x24;   // R/W
    int   MPU6050_I2C_SLV0_ADDR=0x25;   // R/W
    int   MPU6050_I2C_SLV0_REG=0x26;   // R/W
    int   MPU6050_I2C_SLV0_CTRL=0x27;   // R/W
    int   MPU6050_I2C_SLV1_ADDR=0x28;  // R/W
    int   MPU6050_I2C_SLV1_REG=0x29;   // R/W
    int   MPU6050_I2C_SLV1_CTRL= 0x2A;   // R/W
    int   MPU6050_I2C_SLV2_ADDR = 0x2B;   // R/W
    int   MPU6050_I2C_SLV2_REG =  0x2C ;  // R/W
    int   MPU6050_I2C_SLV2_CTRL = 0x2D  ; // R/W
    int   MPU6050_I2C_SLV3_ADDR = 0x2E ; // R/W
    int   MPU6050_I2C_SLV3_REG =  0x2F ;   //RW
    int   MPU6050_I2C_SLV3_CTRL = 0x30  ; // R/W
    int   MPU6050_I2C_SLV4_ADDR = 0x31 ;  // R/W
    int   MPU6050_I2C_SLV4_REG =  0x32 ;  // R/W
    int   MPU6050_I2C_SLV4_DO =   0x33 ;  // R/W
    int   MPU6050_I2C_SLV4_CTRL = 0x34 ;  // R/W
    int   MPU6050_I2C_SLV4_DI =   0x35 ;  // R
    int   MPU6050_I2C_MST_STATUS   =  0x36;   // R
    int   MPU6050_INT_PIN_CFG =   0x37;   // R/W
    int   MPU6050_INT_ENABLE =    0x38;   // R/W
    int   MPU6050_INT_STATUS =    0x3A;   // R
    int   MPU6050_ACCEL_XOUT_H =  0x3B;   // R
    int   MPU6050_ACCEL_XOUT_L =  0x3C;   // R
    int   MPU6050_ACCEL_YOUT_H =  0x3D;   // R
    int   MPU6050_ACCEL_YOUT_L =  0x3E;   // R
    int   MPU6050_ACCEL_ZOUT_H =  0x3F;   // R
    int   MPU6050_ACCEL_ZOUT_L =  0x40;   // R
    int   MPU6050_TEMP_OUT_H =    0x41;   // R
    int   MPU6050_TEMP_OUT_L =    0x42;   // R
    int   MPU6050_GYRO_XOUT_H =   0x43;   // R
    int   MPU6050_GYRO_XOUT_L =   0x44;   // R
    int   MPU6050_GYRO_YOUT_H =   0x45;   // R
    int   MPU6050_GYRO_YOUT_L =   0x46;   // R
    int   MPU6050_GYRO_ZOUT_H =   0x47;   // R
    int   MPU6050_GYRO_ZOUT_L =   0x48;   // R
    int   MPU6050_EXT_SENS_DATA_00 =  0x49;   // R
    int   MPU6050_EXT_SENS_DATA_01  = 0x4A;   // R
    int   MPU6050_EXT_SENS_DATA_02  = 0x4B;   // R
    int   MPU6050_EXT_SENS_DATA_03 =  0x4C;   // R
    int   MPU6050_EXT_SENS_DATA_04 =  0x4D;   // R
    int   MPU6050_EXT_SENS_DATA_05 =  0x4E;   // R
    int   MPU6050_EXT_SENS_DATA_06 =  0x4F;   // R
    int   MPU6050_EXT_SENS_DATA_07 =  0x50;   // R
    int   MPU6050_EXT_SENS_DATA_08  = 0x51;   // R
    int   MPU6050_EXT_SENS_DATA_09  = 0x52;   // R
    int   MPU6050_EXT_SENS_DATA_10  = 0x53;   // R
    int   MPU6050_EXT_SENS_DATA_11 =  0x54 ;  // R
    int   MPU6050_EXT_SENS_DATA_12 =  0x55;   // R
    int   MPU6050_EXT_SENS_DATA_13 =  0x56 ;  // R
    int   MPU6050_EXT_SENS_DATA_14 =  0x57 ;  // R
    int   MPU6050_EXT_SENS_DATA_15 =  0x58;   // R
    int   MPU6050_EXT_SENS_DATA_16 =  0x59;   // R
    int   MPU6050_EXT_SENS_DATA_17 =  0x5A;   // R
    int   MPU6050_EXT_SENS_DATA_18 =  0x5B;   // R
    int   MPU6050_EXT_SENS_DATA_19 =  0x5C;   // R
    int   MPU6050_EXT_SENS_DATA_20 =  0x5D;   // R
    int   MPU6050_EXT_SENS_DATA_21 =  0x5E;   // R
    int   MPU6050_EXT_SENS_DATA_22 =  0x5F;   // R
    int   MPU6050_EXT_SENS_DATA_23 =  0x60;   // R
    int   MPU6050_MOT_DETECT_STATUS = 0x61;   // R
    int   MPU6050_I2C_SLV0_DO =   0x63;   // R/W
    int   MPU6050_I2C_SLV1_DO =   0x64;   // R/W
    int   MPU6050_I2C_SLV2_DO =   0x65;   // R/W
    int   MPU6050_I2C_SLV3_DO =   0x66;   // R/W
    int   MPU6050_I2C_MST_DELAY_CTRL= 0x67;  // R/W
    int   MPU6050_SIGNAL_PATH_RESET = 0x68;   // R/W
    int   MPU6050_MOT_DETECT_CTRL   = 0x69;   // R/W
    int   MPU6050_USER_CTRL =     0x6A;   // R/W
    int   MPU6050_PWR_MGMT_1 =    0x6B;   // R/W
    int   MPU6050_PWR_MGMT_2 =    0x6C ;  // R/W
    int   MPU6050_FIFO_COUNTH =   0x72 ;  // R/W
    int   MPU6050_FIFO_COUNTL =   0x73 ;  // R/W
    int   MPU6050_FIFO_R_W =      0x74 ;  // R/W
    int   MPU6050_WHO_AM_I =      0x75 ;  // R
    int MPU6050_D0 = 0;
    int MPU6050_D1 = 1;
    int MPU6050_D2 = 2;
    int MPU6050_D3 = 3;
    int MPU6050_D4 = 4;
    int MPU6050_D5 = 5;
    int MPU6050_D6 = 6;
    int MPU6050_D7 = 7;
    int MPU6050_AUX_VDDIO = MPU6050_D7;  // I2C high: 1=VDD, 0=VLOGIC
    int MPU6050_DLPF_CFG0  =   MPU6050_D0;
    int MPU6050_DLPF_CFG1  =   MPU6050_D1;
    int MPU6050_DLPF_CFG2  =   MPU6050_D2;
    int MPU6050_EXT_SYNC_SET0  = MPU6050_D3;
    int MPU6050_EXT_SYNC_SET1 = MPU6050_D4;
    int MPU6050_EXT_SYNC_SET2 = MPU6050_D5;
    int MPU6050_EXT_SYNC_SET_0 = (0);
    int MPU6050_EXT_SYNC_SET_1 = (bit(MPU6050_EXT_SYNC_SET0));
    int MPU6050_EXT_SYNC_SET_2 = (bit(MPU6050_EXT_SYNC_SET1));
    int MPU6050_EXT_SYNC_SET_3 = (bit(MPU6050_EXT_SYNC_SET1)|bit(MPU6050_EXT_SYNC_SET0));
    int MPU6050_EXT_SYNC_SET_4 = (bit(MPU6050_EXT_SYNC_SET2));
    int MPU6050_EXT_SYNC_SET_5  =(bit(MPU6050_EXT_SYNC_SET2)|bit(MPU6050_EXT_SYNC_SET0));
    int MPU6050_EXT_SYNC_SET_6  =(bit(MPU6050_EXT_SYNC_SET2)|bit(MPU6050_EXT_SYNC_SET1));
    int MPU6050_EXT_SYNC_SET_7 = (bit(MPU6050_EXT_SYNC_SET2)|bit(MPU6050_EXT_SYNC_SET1)|bit(MPU6050_EXT_SYNC_SET0));
    int MPU6050_EXT_SYNC_DISABLED  =   MPU6050_EXT_SYNC_SET_0;
    int MPU6050_EXT_SYNC_TEMP_OUT_L =  MPU6050_EXT_SYNC_SET_1;
    int MPU6050_EXT_SYNC_GYRO_XOUT_L = MPU6050_EXT_SYNC_SET_2;
    int MPU6050_EXT_SYNC_GYRO_YOUT_L = MPU6050_EXT_SYNC_SET_3;
    int MPU6050_EXT_SYNC_GYRO_ZOUT_L = MPU6050_EXT_SYNC_SET_4;
    int MPU6050_EXT_SYNC_ACCEL_XOUT_L= MPU6050_EXT_SYNC_SET_5;
    int MPU6050_EXT_SYNC_ACCEL_YOUT_L= MPU6050_EXT_SYNC_SET_6;
    int MPU6050_EXT_SYNC_ACCEL_ZOUT_L =MPU6050_EXT_SYNC_SET_7;

    // Combined definitions for the DLPF_CFG values
    int MPU6050_DLPF_CFG_0 = (0);
    int MPU6050_DLPF_CFG_1 = (bit(MPU6050_DLPF_CFG0));
    int MPU6050_DLPF_CFG_2 = (bit(MPU6050_DLPF_CFG1));
    int MPU6050_DLPF_CFG_3 = (bit(MPU6050_DLPF_CFG1)|bit(MPU6050_DLPF_CFG0));
    int MPU6050_DLPF_CFG_4 = (bit(MPU6050_DLPF_CFG2));
    int MPU6050_DLPF_CFG_5 = (bit(MPU6050_DLPF_CFG2)|bit(MPU6050_DLPF_CFG0));
    int MPU6050_DLPF_CFG_6 = (bit(MPU6050_DLPF_CFG2)|bit(MPU6050_DLPF_CFG1));
    int MPU6050_DLPF_CFG_7 = (bit(MPU6050_DLPF_CFG2)|bit(MPU6050_DLPF_CFG1)|bit(MPU6050_DLPF_CFG0));

    // Alternative names for the combined definitions
// This name uses the bandwidth (Hz) for the accelometer,
// for the gyro the bandwidth is almost the same.
    int MPU6050_DLPF_260HZ  =  MPU6050_DLPF_CFG_0;
    int MPU6050_DLPF_184HZ    = MPU6050_DLPF_CFG_1;
    int MPU6050_DLPF_94HZ     = MPU6050_DLPF_CFG_2;
    int MPU6050_DLPF_44HZ     = MPU6050_DLPF_CFG_3;
    int MPU6050_DLPF_21HZ     = MPU6050_DLPF_CFG_4;
    int MPU6050_DLPF_10HZ     = MPU6050_DLPF_CFG_5;
    int MPU6050_DLPF_5HZ      = MPU6050_DLPF_CFG_6;
    int MPU6050_DLPF_RESERVED = MPU6050_DLPF_CFG_7;

    // GYRO_CONFIG Register
// The XG_ST, YG_ST, ZG_ST are bits for selftest.
// The FS_SEL sets the range for the gyro.
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_FS_SEL0 = MPU6050_D3;
    int MPU6050_FS_SEL1 = MPU6050_D4;
    int MPU6050_ZG_ST   = MPU6050_D5;
    int MPU6050_YG_ST   = MPU6050_D6;
    int MPU6050_XG_ST   = MPU6050_D7;

    // Combined definitions for the FS_SEL values
    int MPU6050_FS_SEL_0 = (0);
    int MPU6050_FS_SEL_1 = (bit(MPU6050_FS_SEL0));
    int MPU6050_FS_SEL_2 = (bit(MPU6050_FS_SEL1));
    int MPU6050_FS_SEL_3 = (bit(MPU6050_FS_SEL1)|bit(MPU6050_FS_SEL0));

    // Alternative names for the combined definitions
// The name uses the range in degrees per second.
    int MPU6050_FS_SEL_250 = MPU6050_FS_SEL_0;
    int MPU6050_FS_SEL_500  = MPU6050_FS_SEL_1;
    int MPU6050_FS_SEL_1000 = MPU6050_FS_SEL_2;
    int MPU6050_FS_SEL_2000 = MPU6050_FS_SEL_3;

    // ACCEL_CONFIG Register
// The XA_ST, YA_ST, ZA_ST are bits for selftest.
// The AFS_SEL sets the range for the accelerometer.
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_ACCEL_HPF0 = MPU6050_D0;
    int MPU6050_ACCEL_HPF1= MPU6050_D1;
    int MPU6050_ACCEL_HPF2= MPU6050_D2;
    int MPU6050_AFS_SEL0   = MPU6050_D3;
    int MPU6050_AFS_SEL1   = MPU6050_D4;
    int MPU6050_ZA_ST      = MPU6050_D5;
    int MPU6050_YA_ST      = MPU6050_D6;
    int MPU6050_XA_ST      = MPU6050_D7;

    // Combined definitions for the ACCEL_HPF values
    int MPU6050_ACCEL_HPF_0 =(0);
    int MPU6050_ACCEL_HPF_1= (bit(MPU6050_ACCEL_HPF0));
    int MPU6050_ACCEL_HPF_2 =(bit(MPU6050_ACCEL_HPF1));
    int MPU6050_ACCEL_HPF_3 =(bit(MPU6050_ACCEL_HPF1)|bit(MPU6050_ACCEL_HPF0));
    int MPU6050_ACCEL_HPF_4 =(bit(MPU6050_ACCEL_HPF2));
    int MPU6050_ACCEL_HPF_7= (bit(MPU6050_ACCEL_HPF2)|bit(MPU6050_ACCEL_HPF1)|bit(MPU6050_ACCEL_HPF0));

    // Alternative names for the combined definitions
// The name uses the Cut-off frequency.
    int MPU6050_ACCEL_HPF_RESET = MPU6050_ACCEL_HPF_0;
    int MPU6050_ACCEL_HPF_5HZ  =  MPU6050_ACCEL_HPF_1;
    int MPU6050_ACCEL_HPF_2_5HZ = MPU6050_ACCEL_HPF_2;
    int MPU6050_ACCEL_HPF_1_25HZ =MPU6050_ACCEL_HPF_3;
    int MPU6050_ACCEL_HPF_0_63HZ= MPU6050_ACCEL_HPF_4;
    int MPU6050_ACCEL_HPF_HOLD =  MPU6050_ACCEL_HPF_7;

    // Combined definitions for the AFS_SEL values
    int MPU6050_AFS_SEL_0 = (0);
    int MPU6050_AFS_SEL_1 = (bit(MPU6050_AFS_SEL0));
    int MPU6050_AFS_SEL_2 = (bit(MPU6050_AFS_SEL1));
    int MPU6050_AFS_SEL_3 = (bit(MPU6050_AFS_SEL1)|bit(MPU6050_AFS_SEL0));

    // Alternative names for the combined definitions
// The name uses the full scale range for the accelerometer.
    int MPU6050_AFS_SEL_2G = MPU6050_AFS_SEL_0;
    int MPU6050_AFS_SEL_4G=  MPU6050_AFS_SEL_1;
    int MPU6050_AFS_SEL_8G = MPU6050_AFS_SEL_2;
    int MPU6050_AFS_SEL_16G = MPU6050_AFS_SEL_3;

    // FIFO_EN Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_SLV0_FIFO_EN = MPU6050_D0;
    int MPU6050_SLV1_FIFO_EN = MPU6050_D1;
    int MPU6050_SLV2_FIFO_EN = MPU6050_D2;
    int MPU6050_ACCEL_FIFO_EN= MPU6050_D3;
    int MPU6050_ZG_FIFO_EN =   MPU6050_D4;
    int MPU6050_YG_FIFO_EN =   MPU6050_D5;
    int MPU6050_XG_FIFO_EN=    MPU6050_D6;
    int MPU6050_TEMP_FIFO_EN=  MPU6050_D7;

    // I2C_MST_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_MST_CLK0 = MPU6050_D0;
    int MPU6050_I2C_MST_CLK1 = MPU6050_D1;
    int MPU6050_I2C_MST_CLK2 = MPU6050_D2;
    int MPU6050_I2C_MST_CLK3 = MPU6050_D3;
    int MPU6050_I2C_MST_P_NSR= MPU6050_D4;
    int MPU6050_SLV_3_FIFO_EN= MPU6050_D5;
    int MPU6050_WAIT_FOR_ES  = MPU6050_D6;
    int MPU6050_MULT_MST_EN  = MPU6050_D7;

    // Combined definitions for the I2C_MST_CLK
    int MPU6050_I2C_MST_CLK_0 = (0);
    int MPU6050_I2C_MST_CLK_1  = (bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_2 = (bit(MPU6050_I2C_MST_CLK1));
    int MPU6050_I2C_MST_CLK_3 = (bit(MPU6050_I2C_MST_CLK1)|bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_4 = (bit(MPU6050_I2C_MST_CLK2));
    int MPU6050_I2C_MST_CLK_5 = (bit(MPU6050_I2C_MST_CLK2)|bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_6 = (bit(MPU6050_I2C_MST_CLK2)|bit(MPU6050_I2C_MST_CLK1));
    int MPU6050_I2C_MST_CLK_7 = (bit(MPU6050_I2C_MST_CLK2)|bit(MPU6050_I2C_MST_CLK1)|bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_8 = (bit(MPU6050_I2C_MST_CLK3));
    int MPU6050_I2C_MST_CLK_9 = (bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_10 =(bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK1));
    int MPU6050_I2C_MST_CLK_11 =(bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK1)|bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_12 =(bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK2));
    int MPU6050_I2C_MST_CLK_13 =(bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK2)|bit(MPU6050_I2C_MST_CLK0));
    int MPU6050_I2C_MST_CLK_14 =(bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK2)|bit(MPU6050_I2C_MST_CLK1));
    int MPU6050_I2C_MST_CLK_15 =(bit(MPU6050_I2C_MST_CLK3)|bit(MPU6050_I2C_MST_CLK2)|bit(MPU6050_I2C_MST_CLK1)|bit(MPU6050_I2C_MST_CLK0));

    // Alternative names for the combined definitions
// The names uses I2C Master Clock Speed in kHz.
    int MPU6050_I2C_MST_CLK_348KHZ= MPU6050_I2C_MST_CLK_0;
    int MPU6050_I2C_MST_CLK_333KHZ =MPU6050_I2C_MST_CLK_1;
    int MPU6050_I2C_MST_CLK_320KHZ =MPU6050_I2C_MST_CLK_2;
    int MPU6050_I2C_MST_CLK_308KHZ =MPU6050_I2C_MST_CLK_3;
    int MPU6050_I2C_MST_CLK_296KHZ =MPU6050_I2C_MST_CLK_4;
    int MPU6050_I2C_MST_CLK_286KHZ =MPU6050_I2C_MST_CLK_5;
    int MPU6050_I2C_MST_CLK_276KHZ =MPU6050_I2C_MST_CLK_6;
    int MPU6050_I2C_MST_CLK_267KHZ =MPU6050_I2C_MST_CLK_7;
    int MPU6050_I2C_MST_CLK_258KHZ =MPU6050_I2C_MST_CLK_8;
    int MPU6050_I2C_MST_CLK_500KHZ =MPU6050_I2C_MST_CLK_9;
    int MPU6050_I2C_MST_CLK_471KHZ =MPU6050_I2C_MST_CLK_10;
    int MPU6050_I2C_MST_CLK_444KHZ =MPU6050_I2C_MST_CLK_11;
    int MPU6050_I2C_MST_CLK_421KHZ =MPU6050_I2C_MST_CLK_12;
    int MPU6050_I2C_MST_CLK_400KHZ =MPU6050_I2C_MST_CLK_13;
    int MPU6050_I2C_MST_CLK_381KHZ =MPU6050_I2C_MST_CLK_14;
    int MPU6050_I2C_MST_CLK_364KHZ =MPU6050_I2C_MST_CLK_15;

    // I2C_SLV0_ADDR Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV0_RW = MPU6050_D7;

    // I2C_SLV0_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.;;
    int MPU6050_I2C_SLV0_LEN0=    MPU6050_D0;
    int MPU6050_I2C_SLV0_LEN1 =   MPU6050_D1;
    int MPU6050_I2C_SLV0_LEN2  =  MPU6050_D2;
    int MPU6050_I2C_SLV0_LEN3   = MPU6050_D3;
    int MPU6050_I2C_SLV0_GRP     =MPU6050_D4;
    int MPU6050_I2C_SLV0_REG_DIS= MPU6050_D5;
    int MPU6050_I2C_SLV0_BYTE_SW =MPU6050_D6;
    int MPU6050_I2C_SLV0_EN     = MPU6050_D7;

    // A mask for the length
    int MPU6050_I2C_SLV0_LEN_MASK=  0x0F;

    // I2C_SLV1_ADDR Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV1_RW = MPU6050_D7;

    // I2C_SLV1_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV1_LEN0 =   MPU6050_D0;
    int MPU6050_I2C_SLV1_LEN1  =  MPU6050_D1;
    int MPU6050_I2C_SLV1_LEN2   = MPU6050_D2;
    int MPU6050_I2C_SLV1_LEN3    =MPU6050_D3;
    int MPU6050_I2C_SLV1_GRP   =  MPU6050_D4;
    int MPU6050_I2C_SLV1_REG_DIS= MPU6050_D5;
    int MPU6050_I2C_SLV1_BYTE_SW =MPU6050_D6;
    int MPU6050_I2C_SLV1_EN   =   MPU6050_D7;

    // A mask for the length
    int MPU6050_I2C_SLV1_LEN_MASK = 0x0F;

    // I2C_SLV2_ADDR Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV2_RW = MPU6050_D7;

    // I2C_SLV2_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV2_LEN0   = MPU6050_D0;
    int MPU6050_I2C_SLV2_LEN1  =  MPU6050_D1;
    int MPU6050_I2C_SLV2_LEN2  =  MPU6050_D2;
    int MPU6050_I2C_SLV2_LEN3  =  MPU6050_D3;
    int MPU6050_I2C_SLV2_GRP   =  MPU6050_D4;
    int MPU6050_I2C_SLV2_REG_DIS= MPU6050_D5;
    int MPU6050_I2C_SLV2_BYTE_SW =MPU6050_D6;
    int MPU6050_I2C_SLV2_EN     = MPU6050_D7;
    ;
    // A mask for the length
    int MPU6050_I2C_SLV2_LEN_MASK = 0x0F;

    // I2C_SLV3_ADDR Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV3_RW =MPU6050_D7;

    // I2C_SLV3_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV3_LEN0=    MPU6050_D0;
    int MPU6050_I2C_SLV3_LEN1 =   MPU6050_D1;
    int MPU6050_I2C_SLV3_LEN2  =  MPU6050_D2;
    int MPU6050_I2C_SLV3_LEN3   = MPU6050_D3;
    int MPU6050_I2C_SLV3_GRP=     MPU6050_D4;
    int MPU6050_I2C_SLV3_REG_DIS= MPU6050_D5;
    int MPU6050_I2C_SLV3_BYTE_SW= MPU6050_D6;
    int MPU6050_I2C_SLV3_EN      =MPU6050_D7;

    // A mask for the length
    int MPU6050_I2C_SLV3_LEN_MASK= 0x0F;

    // I2C_SLV4_ADDR Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV4_RW= MPU6050_D7;

    // I2C_SLV4_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_MST_DLY0  =   MPU6050_D0;
    int MPU6050_I2C_MST_DLY1   =  MPU6050_D1;
    int MPU6050_I2C_MST_DLY2    = MPU6050_D2;
    int MPU6050_I2C_MST_DLY3   =  MPU6050_D3;
    int MPU6050_I2C_MST_DLY4    = MPU6050_D4;
    int MPU6050_I2C_SLV4_REG_DIS= MPU6050_D5;
    int MPU6050_I2C_SLV4_INT_EN  =MPU6050_D6;
    int MPU6050_I2C_SLV4_EN      =MPU6050_D7;
    ;
    // A mask for the delay
    int MPU6050_I2C_MST_DLY_MASK = 0x1F;

    // I2C_MST_STATUS Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV0_NACK= MPU6050_D0;
    int MPU6050_I2C_SLV1_NACK =MPU6050_D1;
    int MPU6050_I2C_SLV2_NACK =MPU6050_D2;
    int MPU6050_I2C_SLV3_NACK =MPU6050_D3;
    int MPU6050_I2C_SLV4_NACK =MPU6050_D4;
    int MPU6050_I2C_LOST_ARB  =MPU6050_D5;
    int MPU6050_I2C_SLV4_DONE =MPU6050_D6;
    int MPU6050_PASS_THROUGH  =MPU6050_D7;

    // I2C_PIN_CFG Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_CLKOUT_EN=MPU6050_D0;
    int MPU6050_I2C_BYPASS_EN = MPU6050_D1;
    int MPU6050_FSYNC_INT_EN = MPU6050_D2;
    int MPU6050_FSYNC_INT_LEVEL =  MPU6050_D3;
    int MPU6050_INT_RD_CLEAR  =  MPU6050_D4;
    int MPU6050_LATCH_INT_EN  =  MPU6050_D5;
    int MPU6050_INT_OPEN= MPU6050_D6;
    int MPU6050_INT_LEVEL=MPU6050_D7;

    // INT_ENABLE Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_DATA_RDY_EN  =  MPU6050_D0;
    int MPU6050_I2C_MST_INT_EN = MPU6050_D3;
    int MPU6050_FIFO_OFLOW_EN  = MPU6050_D4;
    int MPU6050_ZMOT_EN= MPU6050_D5;
    int MPU6050_MOT_EN=  MPU6050_D6;
    int MPU6050_FF_EN=   MPU6050_D7;

    // INT_STATUS Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_DATA_RDY_INT  = MPU6050_D0;
    int MPU6050_I2C_MST_INT   = MPU6050_D3;
    int MPU6050_FIFO_OFLOW_INT= MPU6050_D4;
    int MPU6050_ZMOT_INT=MPU6050_D5;
    int MPU6050_MOT_INT= MPU6050_D6;
    int MPU6050_FF_INT=  MPU6050_D7;

    // MOT_DETECT_STATUS Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_MOT_ZRMOT= MPU6050_D0;
    int MPU6050_MOT_ZPOS = MPU6050_D2;
    int MPU6050_MOT_ZNEG = MPU6050_D3;
    int MPU6050_MOT_YPOS = MPU6050_D4;
    int MPU6050_MOT_YNEG = MPU6050_D5;
    int MPU6050_MOT_XPOS = MPU6050_D6;
    int MPU6050_MOT_XNEG = MPU6050_D7;

    // IC2_MST_DELAY_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_I2C_SLV0_DLY_EN = MPU6050_D0;
    int MPU6050_I2C_SLV1_DLY_EN= MPU6050_D1;
    int MPU6050_I2C_SLV2_DLY_EN =MPU6050_D2;
    int MPU6050_I2C_SLV3_DLY_EN= MPU6050_D3;
    int MPU6050_I2C_SLV4_DLY_EN= MPU6050_D4;
    int MPU6050_DELAY_ES_SHADOW =MPU6050_D7;

    // SIGNAL_PATH_RESET Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_TEMP_RESET = MPU6050_D0;
    int MPU6050_ACCEL_RESET =MPU6050_D1;
    int MPU6050_GYRO_RESET  =MPU6050_D2;

    // MOT_DETECT_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_MOT_COUNT0   =   MPU6050_D0;
    int MPU6050_MOT_COUNT1   =   MPU6050_D1;
    int MPU6050_FF_COUNT0=MPU6050_D2;
    int MPU6050_FF_COUNT1=MPU6050_D3;
    int MPU6050_ACCEL_ON_DELAY0 = MPU6050_D4;
    int MPU6050_ACCEL_ON_DELAY1 = MPU6050_D5;

    // Combined definitions for the MOT_COUNT
    int MPU6050_MOT_COUNT_0 = (0);
    int MPU6050_MOT_COUNT_1 = (bit(MPU6050_MOT_COUNT0));
    int MPU6050_MOT_COUNT_2 = (bit(MPU6050_MOT_COUNT1));
    int MPU6050_MOT_COUNT_3 = (bit(MPU6050_MOT_COUNT1)|bit(MPU6050_MOT_COUNT0));

    // Alternative names for the combined definitions
    int MPU6050_MOT_COUNT_RESET = MPU6050_MOT_COUNT_0;

    // Combined definitions for the FF_COUNT
    int MPU6050_FF_COUNT_0 = (0);
    int MPU6050_FF_COUNT_1 = (bit(MPU6050_FF_COUNT0));
    int MPU6050_FF_COUNT_2 = (bit(MPU6050_FF_COUNT1));
    int MPU6050_FF_COUNT_3 = (bit(MPU6050_FF_COUNT1)|bit(MPU6050_FF_COUNT0));

    // Alternative names for the combined definitions
    int MPU6050_FF_COUNT_RESET = MPU6050_FF_COUNT_0;

    // Combined definitions for the ACCEL_ON_DELAY
    int MPU6050_ACCEL_ON_DELAY_0 = (0);
    int MPU6050_ACCEL_ON_DELAY_1=  (bit(MPU6050_ACCEL_ON_DELAY0));
    int MPU6050_ACCEL_ON_DELAY_2= (bit(MPU6050_ACCEL_ON_DELAY1));
    int MPU6050_ACCEL_ON_DELAY_3= (bit(MPU6050_ACCEL_ON_DELAY1)|bit(MPU6050_ACCEL_ON_DELAY0));

    // Alternative names for the ACCEL_ON_DELAY
    int MPU6050_ACCEL_ON_DELAY_0MS = MPU6050_ACCEL_ON_DELAY_0;
    int MPU6050_ACCEL_ON_DELAY_1MS = MPU6050_ACCEL_ON_DELAY_1;
    int MPU6050_ACCEL_ON_DELAY_2MS = MPU6050_ACCEL_ON_DELAY_2;
    int MPU6050_ACCEL_ON_DELAY_3MS = MPU6050_ACCEL_ON_DELAY_3;

    // USER_CTRL Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_SIG_COND_RESET= MPU6050_D0;
    int MPU6050_I2C_MST_RESET = MPU6050_D1;
    int MPU6050_FIFO_RESET    = MPU6050_D2;
    int MPU6050_I2C_IF_DIS    = MPU6050_D4 ;  // must be 0 for MPU-6050
    int MPU6050_I2C_MST_EN   =  MPU6050_D5;
    int MPU6050_FIFO_EN= MPU6050_D6;

    // PWR_MGMT_1 Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_CLKSEL0   =   MPU6050_D0;
    int MPU6050_CLKSEL1   =   MPU6050_D1;
    int MPU6050_CLKSEL2   =   MPU6050_D2;
    int MPU6050_TEMP_DIS  =   MPU6050_D3 ;   // 1: disable temperature sensor
    int MPU6050_CYCLE= MPU6050_D5;    // 1: sample and sleep
    int MPU6050_SLEEP= MPU6050_D6  ;  // 1: sleep mode
    int MPU6050_DEVICE_RESET= MPU6050_D7  ;  // 1: reset to default values

    // Combined definitions for the CLKSEL
    int MPU6050_CLKSEL_0= (0);
    int MPU6050_CLKSEL_1 =(bit(MPU6050_CLKSEL0));
    int MPU6050_CLKSEL_2 =(bit(MPU6050_CLKSEL1));
    int MPU6050_CLKSEL_3= (bit(MPU6050_CLKSEL1)|bit(MPU6050_CLKSEL0));
    int MPU6050_CLKSEL_4 =(bit(MPU6050_CLKSEL2));
    int MPU6050_CLKSEL_5 =(bit(MPU6050_CLKSEL2)|bit(MPU6050_CLKSEL0));
    int MPU6050_CLKSEL_6 =(bit(MPU6050_CLKSEL2)|bit(MPU6050_CLKSEL1));
    int MPU6050_CLKSEL_7= (bit(MPU6050_CLKSEL2)|bit(MPU6050_CLKSEL1)|bit(MPU6050_CLKSEL0));

    // Alternative names for the combined definitions
    int MPU6050_CLKSEL_INTERNAL =   MPU6050_CLKSEL_0;
    int MPU6050_CLKSEL_X=    MPU6050_CLKSEL_1;
    int MPU6050_CLKSEL_Y=    MPU6050_CLKSEL_2;
    int MPU6050_CLKSEL_Z=    MPU6050_CLKSEL_3;
    int MPU6050_CLKSEL_EXT_32KHZ  = MPU6050_CLKSEL_4;
    int MPU6050_CLKSEL_EXT_19_2MHZ =MPU6050_CLKSEL_5;
    int MPU6050_CLKSEL_RESERVED    =MPU6050_CLKSEL_6;
    int MPU6050_CLKSEL_STOP= MPU6050_CLKSEL_7;

    // PWR_MGMT_2 Register
// These are the names for the bits.
// Use these only with the bit() macro.
    int MPU6050_STBY_ZG=MPU6050_D0;
    int MPU6050_STBY_YG=MPU6050_D1;
    int MPU6050_STBY_XG=MPU6050_D2;
    int MPU6050_STBY_ZA=MPU6050_D3;
    int MPU6050_STBY_YA=MPU6050_D4;
    int MPU6050_STBY_XA=MPU6050_D5;
    int MPU6050_LP_WAKE_CTRL0= MPU6050_D6;
    int MPU6050_LP_WAKE_CTRL1 =MPU6050_D7;

    // Combined definitions for the LP_WAKE_CTRL
    int MPU6050_LP_WAKE_CTRL_0= (0);
    int MPU6050_LP_WAKE_CTRL_1 =(bit(MPU6050_LP_WAKE_CTRL0));
    int MPU6050_LP_WAKE_CTRL_2 = (bit(MPU6050_LP_WAKE_CTRL1));
    int MPU6050_LP_WAKE_CTRL_3 = (bit(MPU6050_LP_WAKE_CTRL1)|bit(MPU6050_LP_WAKE_CTRL0));

    // Alternative names for the combined definitions
// The names uses the Wake-up Frequency.
    int MPU6050_LP_WAKE_1_25HZ = MPU6050_LP_WAKE_CTRL_0;
    int MPU6050_LP_WAKE_2_5HZ=  MPU6050_LP_WAKE_CTRL_1;
    int MPU6050_LP_WAKE_5HZ   = MPU6050_LP_WAKE_CTRL_2;
    int MPU6050_LP_WAKE_10HZ  = MPU6050_LP_WAKE_CTRL_3;
    int MPU6050_I2C_ADDRESS = 0x68;
    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();
    DeviceInterfaceModule cdim;
    I2cDevice acc;

    @Override
    public void init() {
        cdim = hardwareMap.deviceInterfaceModule.get("dim");
        acc = hardwareMap.i2cDevice.get("acc");
    }
    public int bit(int x)
    {
        return x * x;
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {

        // byte a[] = acc.getI2cReadCache();
        //String b = a.toString();
        // telemetry.addData(b,0);


        startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        runtime.reset();
        telemetry.addData("Null Op Init Loop", runtime.toString());
        acc.notify();

    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        telemetry.addData(acc.getDeviceName(), 0);
        telemetry.addData("1 Start", "Accel_Test started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());
        int b1 = acc.getI2cReadCache()[0];
        int b2 = acc.getI2cReadCache()[1];
        int b3 = acc.getI2cReadCache()[2];
        int b4 = acc.getI2cReadCache()[3];
        int b5 = acc.getI2cReadCache()[4];
        int b6 = acc.getI2cReadCache()[5];
        int b7 = acc.getI2cReadCache()[6];
        int b8 = acc.getI2cReadCache()[7];
        telemetry.addData(Integer.toString(b1), 0);
        telemetry.addData(Integer.toString(b2), 0);
        telemetry.addData(Integer.toString(b3), 0);
        telemetry.addData(Integer.toString(b4), 0);
        telemetry.addData(Integer.toString(b5), 0);
        telemetry.addData(Integer.toString(b6), 0);
        telemetry.addData(Integer.toString(b7), 0);
    }
}
