package com.aungpyaesone.testeverything.digitalsignature

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.aungpyaesone.testeverything.R
import com.github.gcacace.signaturepad.views.SignaturePad
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_digital_sign.*
import java.io.*
import java.io.FileDescriptor.out


class DigitalSignActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE =
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        private fun verifyStoragePermission(activity: Activity) {
            val permission = ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            );

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                );
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digital_sign)
        verifyStoragePermission(
            this
        )
        setUp()
    }

    private fun setUp() {
        signature_pad.setOnSignedListener(
            object : SignaturePad.OnSignedListener{
                override fun onStartSigning() {
                showSnackBar("OnStartSigning")
                }

                override fun onSigned() {
                ClearButton.isEnabled = true
                SaveButton.isEnabled = true
                }

                override fun onClear() {
                    ClearButton.isEnabled = false
                    SaveButton.isEnabled = false
                }
            }
        )

        ClearButton.setOnClickListener{
            signature_pad.clear()
        }

        SaveButton.setOnClickListener {
            val bitMapSignature = signature_pad.signatureBitmap
            if(addJpgSignatureToGallery(bitMapSignature)){
            showSnackBar("Signature saved into the Gallery")
            }
            else{
                showSnackBar("Unable to store the signature")
            }
            if(addSvgSignatureToGallery(signature_pad.signatureSvg)){
            showSnackBar("SVG Signature saved into the Gallery")
            }
            else{
                showSnackBar("Unable to store the SVG signature")
            }
        }
    }


    private fun getAlbumStorageDir(albumName:String):File{
        // Get the directory for the user's public pictures directory
        val file = File(applicationContext.getExternalFilesDir(
            Environment.DIRECTORY_PICTURES
        ),albumName)
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file
    }


    /// Save Bitmap to JPG
    @Throws(IOException::class)
    fun saveBitmapToJPG(bitmap: Bitmap, photo: File) {
        val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas:Canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream: OutputStream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream)
        stream.flush()
        stream.close()
    }

    private fun addJpgSignatureToGallery(signature:Bitmap):Boolean{
        val result
                = false
        try{
            val photo = File(getAlbumStorageDir("Signature Pad"),
                String.format("Signature_%d.png", System.currentTimeMillis()))
            saveBitmapToJPG(signature,photo)

        }
        catch (e:IOException){
            e.printStackTrace()
        }
        return result;
    }

    private fun scanMediaFile(photo: File){
       MediaScannerConnection.scanFile(applicationContext,
          Array<String>(1){photo.absolutePath},
           null
       ) { path, uri ->
           val mediaScanIntent = Intent(Intent.ACTION_MEDIA_MOUNTED)
           mediaScanIntent.data = uri
            this.sendBroadcast(mediaScanIntent,path)
       }
    }

    private fun addSvgSignatureToGallery(signatureSvg: String):Boolean{
        var result = false
        try{
            val svgFile = File(getAlbumStorageDir("SignaturePad"),String.format("Signature_%d.svg", System.currentTimeMillis()))
            val stream:OutputStream = FileOutputStream(svgFile)
            val writer:OutputStreamWriter = OutputStreamWriter(stream)
            writer.write(signatureSvg)
            writer.close()
            stream.flush()
            stream.close()
            scanMediaFile(svgFile)
            result = true
        }catch (e:IOException)
        {
            e.printStackTrace()
        }
        return result
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isEmpty()
                    || grantResults[0] !== PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(
                        applicationContext,
                        "Cannot write images to external storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }



    private fun showSnackBar(message:String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }
}
