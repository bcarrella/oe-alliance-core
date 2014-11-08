SUMMARY = "OpenViX-HD YouViX skins by rossi2000 and simonsez"
MAINTAINER = "rossi2000"

require conf/license/license-gplv2.inc

inherit gitpkgv allarch

EPSM = "enigma2-plugin-skins"
SRCREV = "${AUTOREV}"
PV = "2.3+git${SRCPV}"
PKGV = "2.3+git${GITPKGV}"
PR = "r9"

PACKAGES = "${EPSM}-youvix-red ${EPSM}-youvix-green"

SRC_URI = "git://github.com/OpenViX/vixhd-skins.git;protocol=git"

FILES_${EPSM}-youvix-red = "/usr/share/enigma2/YouViX-Red"
FILES_${EPSM}-youvix-green = "/usr/share/enigma2/YouViX-Green"

S = "${WORKDIR}/git"

do_compile_append() {
   python -O -m compileall ${S}
}

do_install() {
    install -d ${D}/usr/share/enigma2
    mv ${S}/* ${D}/usr/share/enigma2/
    chmod -R a+rX ${D}/usr/share/enigma2/
}
