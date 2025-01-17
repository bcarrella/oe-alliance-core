SUMMARY = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

KERNEL_RELEASE = "4.0.1"

COMPATIBLE_MACHINE = "^(7000s|7005s|7100s|7105s|7210s|7215s|7220s|7225s|7300s|7400s)$"

inherit kernel machine_kernel_pr

SRC_URI[md5sum] = "53d1614e476bc1141b35266cb31ba091"
SRC_URI[sha256sum] = "385d8efec92b5d3bc8e16c37673e4a2a38a6541b684311650040aa5d67508c3e"

LIC_FILES_CHKSUM = "file://${WORKDIR}/linux-${PV}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

MACHINE_KERNEL_PR:append = ".3"

RPROVIDES:kernel-image = "kernel-${KERNEL_IMAGETYPE}"

SRC_URI += "https://source.mynonpublic.com/ceryon/ceryon-linux-${PV}.tgz \
        file://defconfig \
        file://dvb-usb-i2c_duplicate.patch \
        file://kernel-add-support-for-gcc6.patch \
        file://kernel-add-support-for-gcc7.patch \
        file://kernel-add-support-for-gcc8.patch \
        file://kernel-add-support-for-gcc9.patch \
        file://kernel-add-support-for-gcc10.patch \
        file://kernel-add-support-for-gcc11.patch \
        file://kernel-add-support-for-gcc12.patch \
        file://0001-Support-TBS-USB-drivers-for-4.0.1-kernel.patch \
        file://0001-TBS-fixes-for-4.0.1-kernel.patch \
        file://0001-STV-Add-PLS-support.patch \
        file://0001-STV-Add-SNR-Signal-report-parameters.patch \
        file://blindscan2.patch \
        file://0001-stv090x-optimized-TS-sync-control.patch \
        file://0002-add-brcm-chips.patch \
        file://0003-cp1emu-do-not-use-bools-for-arithmetic.patch \
        file://0004-log2-give-up-on-gcc-constant-optimizations.patch \
        file://move-default-dialect-to-SMB3.patch \
        file://fix-never-be-null_outside-array-bounds-gcc-12.patch \
"

S = "${WORKDIR}/linux-${PV}"
B = "${WORKDIR}/build"

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "tmp"

KERNEL_EXTRA_ARGS = "EXTRA_CFLAGS=-Wno-attribute-alias"

FILES:${KERNEL_PACKAGE_NAME}-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}*"

kernel_do_install:append() {
    ${STRIP} ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
    gzip -9c ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION} > ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
    rm ${D}/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
}

pkg_postinst:kernel-image () {
    if [ "x$D" == "x" ]; then
        if [ -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz ] ; then
            flash_erase /dev/${MTD_KERNEL} 0 0
            nandwrite -p /dev/${MTD_KERNEL} /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
            rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
        fi
    fi
    true
}

do_rm_work() {
}

# extra tasks
addtask kernel_link_images after do_compile before do_install
