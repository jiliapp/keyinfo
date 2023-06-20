package com.bbbang.key

import oshi.SystemInfo
import oshi.hardware.HardwareAbstractionLayer

class DeviceInfo {

    companion object{
         fun genDeviceInfo() {
            //diskStores networkIfs cpu
            //cpuSerial  mainBoardUuid

            println("\n======================================================")
            val si = SystemInfo()
            val hal: HardwareAbstractionLayer = si.hardware
            println("\n磁盘-> \n\n" + hal.diskStores)
            println("\n======================================================")
            println("\n网络-> \n\n" + hal.networkIFs)
            println("\n======================================================")
            println("\n处理器-> \n\n" + hal.processor)
            println("\n======================================================\n")
            val cupId: String = hal.processor.processorIdentifier.processorID
            val mainBoardSerial: String = hal.computerSystem.hardwareUUID
            println("处理器序列号(*必须) -> $cupId")
            println("主板序列号(*必须)   -> $mainBoardSerial\n")
        }
    }
}