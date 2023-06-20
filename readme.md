#### main函数获取硬件信息
1. 普通jar包打包成fatJar
```kotlin
tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes("Main-Class" to "com.bbbang.key.MainKt")
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

```
2. 获得oshi硬件信息,用于license生成
````ignorelang

java -jar keyinfo-1.0.0.jar

磁盘->

[\\.\PHYSICALDRIVE1: (model: Samsung SSD 750 EVO 120GB (标准磁盘驱动器) - S/N: S2S6NWAGA00842F) size: 120.0 GB, reads: 2206053 (74.2 GiB), writes: 2997639 (60.7 GiB), xfer: 3705626, \\.\PHYSICALDRIVE0: (model: WDC WD10EZEX-00BN5A0 (
标准磁盘驱动器) - S/N:      WD-WCC3F3TH1P6V) size: 1.0 TB, reads: 595316 (41.2 GiB), writes: 649123 (16.7 GiB), xfer: 12438577]

======================================================

网络->

[Name: eth4 (Realtek PCIe GbE Family Controller) [IfAlias=以太网]
  MAC Address: 40:8d:5c:2a:fe:46
  MTU: 1492, Speed: 1000000000
  IPv4: [192.168.1.6/24]
  IPv6: [240e:399:f4a:7ed0:64bc:dedd:5a37:dfd1/64, 240e:399:f4a:7ed0:9c53:1d34:29f0:6dba/128, fe80:0:0:0:64bc:dedd:5a37:dfd1/128]
  Traffic: received 10248337 packets/13.1 GiB (0 err, 0 drop); transmitted 2868418 packets/315.5 MiB (0 err, 0 coll);, Name: eth7 (TAP-Windows Adapter V9) [IfAlias=SSTAP 1]
  MAC Address: 00:ff:9b:37:b7:39
  MTU: 1500, Speed: 100000000
  IPv4: []
  IPv6: [fe80:0:0:0:c8af:30dc:eb6:b415/64]
  Traffic: received 0 packets/0 bytes (0 err, 0 drop); transmitted 0 packets/0 bytes (0 err, 0 coll);, Name: eth8 (Hyper-V Virtual Ethernet Adapter) [IfAlias=vEthernet (Default Switch)]
  MAC Address: 00:15:5d:83:b7:ff
  MTU: 1500, Speed: 10000000000
  IPv4: [172.27.0.1/20]
  IPv6: [fe80:0:0:0:6581:9ac:a4ef:2279/64]
  Traffic: received 0 packets/0 bytes (0 err, 0 drop); transmitted 0 packets/1.8 MiB (0 err, 0 coll);, Name: eth26 (Hyper-V Virtual Ethernet Adapter #2) [IfAlias=vEthernet (WSL)]
  MAC Address: 00:15:5d:53:cc:c7
  MTU: 1500, Speed: 10000000000
  IPv4: [172.25.16.1/20]
  IPv6: [fe80:0:0:0:8858:6852:1eff:f249/64]
  Traffic: received 9568 packets/781.3 KiB (0 err, 0 drop); transmitted 10532 packets/26.9 MiB (0 err, 0 coll);]

======================================================

处理器->

Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz
 1 physical CPU package(s)
 4 physical CPU core(s)
 4 logical CPU(s)
Identifier: Intel64 Family 6 Model 60 Stepping 3
ProcessorID: BFEBFBFF000306C3
Microarchitecture: Haswell (Client)

======================================================

处理器序列号(*必须) -> BFEBFBFF000306C3
主板序列号(*必须)   -> 038D0240-045C-052A-FE06-460700080009

====================================================== license end


````
3. 在平台生成license
````ignorelang
    处理器序列号(*必须) -> BFEBFBFF000306C3
    主板序列号(*必须)   -> 038D0240-045C-052A-FE06-460700080009
  
    1541693333435453442_6924670640316521671.lic
````
4. 安装license
````ignorelang
    java -jar keymgr install  swagger.lic
````
5. 验证license
````ignorelang
java -jar keymgr.jar load

[subject="bbbang", holder="CN=newbee.com, OU=newbee, O=北京牛逼科技有限公司, C=CN", issuer="CN=BBBang RSA OV SSL CA 2008, OU=BBBang Group, O=BBBang nv-sa, C=CN", issued=Mon Jun 19 16:43:14 CST 2023, notBefore=Mon Jun 19 16:43:14 CST 2023, notAfter=Thu Jun 13 20:46:14 CST 2024, consumerType="user", consumerAmount=1, info="仅授权北京牛逼科技有限公司使用"]



````
