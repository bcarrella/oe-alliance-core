SUMMARY = "grab for beyonwiz Model ${MACHINE}"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^(beyonwizv2)$"

SRCDATE = "20180301"

PV = "${SRCDATE}"
PR = "r0"

RPROVIDES:${PN}  = "aio-grab"
RREPLACES:${PN}  = "aio-grab"
RCONFLICTS:${PN} = "aio-grab"

SRC_URI = "http://source.mynonpublic.com/beyonwiz/${MACHINE}-grab-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/grab ${D}/${bindir}
}

do_package_qa() {
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
FILES:${PN}  = "${bindir}/grab"

SRC_URI[md5sum] = "4617805f460607e15a0f322ee25113c7"
SRC_URI[sha256sum] = "a20b6a814f262c4cd2d18bdb74f2b718d921ff24fc07e66a032fec553072374e"
