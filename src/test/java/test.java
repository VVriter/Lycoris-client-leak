import okhttp3.*;

public class test {
    public static void main(String[] args) {
        String apiKey = "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";
        String prompt = "Привет";
        int length = 50;

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("prompt", prompt)
                .add("length", Integer.toString(length))
                .build();

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/davinci/completions")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
