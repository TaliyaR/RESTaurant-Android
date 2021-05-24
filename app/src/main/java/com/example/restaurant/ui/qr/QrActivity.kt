package com.example.restaurant.ui.qr

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Bundle
import android.util.Size
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.restaurant.R
import com.example.restaurant.utils.ImageAnalyzer
import com.example.restaurant.viewmodel.qr.QrActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_qr.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class QrActivity : AppCompatActivity() {

    companion object {

        const val REQUEST_CODE_PERMISSIONS = 1
    }

    private val viewModel: QrActivityViewModel by viewModels()

    private lateinit var cameraExecutor: ExecutorService
    private var permissionsForRequest: Pair<String, Pair<() -> Unit, () -> Unit>>? = null
    private var isScannerActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        topAppBar.setNavigationOnClickListener { finish() }

        cameraExecutor = Executors.newSingleThreadExecutor()

        withPermission(
            Manifest.permission.CAMERA,
            {
                startCamera()
            },
            {
                Toast.makeText(this, getString(R.string.error_permission), Toast.LENGTH_SHORT)
                    .show()
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    fun withPermission(permission: String, granted: () -> Unit, denied: () -> Unit) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            granted()
        } else {
            permissionsForRequest = Pair(permission, Pair(granted, denied))
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun startCamera() {
        val cameraProviderFeature = ProcessCameraProvider.getInstance(this)
        cameraProviderFeature.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFeature.get()

            val size =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Size(1080, 1920)
                } else {
                    Size(1920, 1080)
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            val preview = Preview.Builder()
                .setTargetResolution(size)
                .build()
                .also {
                    it.setSurfaceProvider(preview_view.surfaceProvider)
                }

            val imageAnalysis = ImageAnalysis.Builder()
                .setTargetResolution(size)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, ImageAnalyzer { qrResult ->
                        preview_view.post {
                            if (isScannerActive) {
                                // TODO get {qrResult.text}
                            }
                        }
                    })
                }

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageAnalysis,
            )

        }, ContextCompat.getMainExecutor(this))
    }
}