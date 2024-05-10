package com.hridoy.chatgemini.common.utils

import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

@Suppress("UtilityClassWithPublicConstructor")
class RootUtil {
    companion object {
        private val Tag = RootUtil::class.java.simpleName
        private const val PathToWhich = "/system/xbin/which"
        private const val CmdSu = "su"
        private val PathsSu = arrayOf(
            "/system/app/Superuser.apk",
            "/system/etc/init.d/99SuperSUDaemon",
            "/dev/com.koushikdutta.superuser.daemon/",
            "/system/xbin/daemonsu",
            "/system/xbin/busybox",
            "/system/xbin/su",
            "/system/sd/xbin/su",
            "/system/bin/su",
            "/system/bin/failsafe/su",
            "/su/bin/su",
            "/sbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/data/local/su",
        )

        fun isDeviceRooted(): Boolean {
            return checkRootBuild() || checkRootApps() || checkRootSu()
        }

        private fun checkRootBuild(): Boolean {
            val buildTags = Build.TAGS
            val ret = buildTags != null && buildTags.contains("test-keys")
            if (ret) {
                Log.e(Tag, "Root check is false(Test build or custom build)")
            }
            return ret
        }

        private fun checkRootApps(): Boolean {
            for (path in PathsSu) {
                if (File(path).exists()) {
                    Log.e(Tag, "Root check is false(SU application is found)")
                    return true
                }
            }
            return false
        }


        @Suppress("SwallowedException", "TooGenericExceptionCaught")
        private fun checkRootSu(): Boolean {
            var process: Process? = null
            val ret = try {
                process = Runtime.getRuntime().exec(arrayOf(PathToWhich, CmdSu))
                val inputStream = BufferedReader(InputStreamReader(process.inputStream))
                inputStream.readLine() != null
            } catch (t: Throwable) {
                false
            } finally {
                process?.destroy()
            }
            if (ret.not()) {
                Log.e(Tag, "Root check is false(SU is enabled)")
            }
            return ret
        }
    }
}
