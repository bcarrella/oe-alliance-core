DESCIPTION = "S3 cold boot"
MAINTAINER = "vuplus team"
LICENSE = "CLOSED"

PV = "1.0"
PR = "r1"

SRC_URI = "file://coldboot file://coldboot.sh"

inherit update-rc.d
INITSCRIPT_NAME = "coldboot"
INITSCRIPT_PARAMS = "defaults 30"

do_install() {
	install -d ${D}/etc/init.d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/coldboot.sh ${D}/etc/init.d/coldboot
	install -m 0755 ${WORKDIR}/coldboot ${D}/usr/bin/coldboot
}
