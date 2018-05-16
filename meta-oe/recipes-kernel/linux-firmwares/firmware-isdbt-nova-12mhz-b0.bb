require linux-firmware.inc

SUMMARY = "Firmware for isdbt_nova_12mhz_b0.inp"

SRCREV = "13f0b6bda7b567d29c747196aa65ad82b18651ca"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 isdbt_nova_12mhz_b0.inp ${D}${nonarch_base_libdir}/firmware
}
