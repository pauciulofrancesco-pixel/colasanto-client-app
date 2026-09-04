package it.colasanto.personalstudio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.OutputStream;

@CapacitorPlugin(name = "ColasantoFiles")
public class ColasantoFilesPlugin extends Plugin {

    @PluginMethod
    public void saveBase64(PluginCall call) {
        String base64 = call.getString("base64");
        String fileName = call.getString("fileName", "COLASANTO_documento.pdf");
        String mimeType = call.getString("mimeType", "application/octet-stream");
        if (base64 == null || base64.isEmpty()) {
            call.reject("Contenuto del file mancante");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(call, intent, "saveFileResult");
    }

    @ActivityCallback
    private void saveFileResult(PluginCall call, ActivityResult result) {
        if (call == null) return;
        if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null || result.getData().getData() == null) {
            call.reject("Salvataggio annullato");
            return;
        }
        Uri uri = result.getData().getData();
        String base64 = call.getString("base64");
        try (OutputStream out = getContext().getContentResolver().openOutputStream(uri, "w")) {
            if (out == null) {
                call.reject("Impossibile aprire il file selezionato");
                return;
            }
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
            out.write(bytes);
            out.flush();
            JSObject ret = new JSObject();
            ret.put("uri", uri.toString());
            ret.put("saved", true);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Impossibile salvare il file: " + e.getMessage(), e);
        }
    }
}
