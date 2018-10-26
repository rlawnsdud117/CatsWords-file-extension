package com.example.exts;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    private static String Str_dateitme = "" ;
    private static String Str_signature = "";
    private static String Str_opewnith ="" ;
    private static String Str_execution = "" ;
    private static String Str_content =  "";
    private static String Str_orgranization ="" ;
    private static String Str_description = "";
    private static String Str_prefix = "";
    private static String Str_identifier = "";
    Document doc = null;
    ListView ListView1;
    EditText editText;
    Button button;
    Context context;
    private boolean newtwork = true;
    private ArrayList<String> list = null;

    public String a = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText1);
        button = findViewById(R.id.button);
        ListView listView = findViewById(R.id.ListView1);
        ArrayList<String> arraylist = new ArrayList<String>();
        ArrayList<String> arraylist1 = new ArrayList<String>();



        editText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == event.KEYCODE_ENTER) {
                    button.performClick();

                    return true;
                }
                return false;
            }
        });

    }

    public void onClick(View view) {
        ListView listView = findViewById(R.id.ListView1);



        if (editText.getText().toString().trim().equals("")) {

            Toast.makeText(getBaseContext(), "입력 해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editText.length() < 3) {
            Toast.makeText(getBaseContext(), "최소 3골자 입력 해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }


        ConnectivityManager cManager = null;
        cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cManager != null) {

            NetworkInfo mobile;
            NetworkInfo wifi;

            mobile = cManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            wifi = cManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            // ConnectivityManager.TYPE_MOBILE이 리턴되지 않는 경우를 대비한 방어 코드 추가
            if (mobile != null && mobile.isConnected() || wifi.isConnected()) {
                GetXMLTask XMLURL = new GetXMLTask();
                XMLURL.execute("https://exts.kr/api/claw.php?q=" + editText.getText());
            } else {
                Toast.makeText(MainActivity.this, "네트워크 연결을 확인해 주세요.", Toast.LENGTH_LONG).show();
            }

        } else {
            return;
}
    }



//private inner class extending AsyncTask
    private class GetXMLTask extends AsyncTask<String, Void, Document> {


        @Override
        protected Document doInBackground(String... urls) {


            URL url;
            try {

                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();


            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }


        @Override
        protected void onPostExecute(Document doc) {

            test(doc);


        }

    }

    public  void  test(Document doc) {
        final ArrayList<String> arraylist_identifier = new ArrayList<String>();
        final ArrayList<String> arraylist_prefix = new ArrayList<String>();
        final ArrayList<String> arraylist_orgranization = new ArrayList<String>();
        final ArrayList<String> arraylist_description = new ArrayList<String>();
        final ArrayList<String> arraylist_execution = new ArrayList<String>();
        final ArrayList<String> arraylist_content = new ArrayList<String>();
        final ArrayList<String> arraylist_opewnith = new ArrayList<String>();
        final ArrayList<String> arraylist_signature = new ArrayList<String>();

        final ArrayList<String> arraylist_dateitme = new ArrayList<String>();

        //dateitme
        String s = "";

        //     try {
            // claw/list/item 태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
        NodeList item_List = doc.getElementsByTagName("item");
            //claw/list/item 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환
            for (int i = 0; i < item_List.getLength(); i++) {

                Node node = item_List.item(i); //노드의 갯수의 길이 인덱스 i에 저장된 노드를 구한다.
                Element fastElan = (Element) node;// // "item" 태그를 가지는 노드를 찾는다
                NodeList identifier = fastElan.getElementsByTagName("identifier");// 파일 확장자
                NodeList prefix = fastElan.getElementsByTagName("prefix");  // 확장자 뒷
                NodeList orgranization = fastElan.getElementsByTagName("orgranization");//회사
                NodeList description = fastElan.getElementsByTagName("description");// 간단 요약설명
                NodeList execution = fastElan.getElementsByTagName("execution"); // 실행정보
                NodeList content = fastElan.getElementsByTagName("content");//  파일 상세정보
                NodeList opewnith = fastElan.getElementsByTagName("openwith");// 여는 프로그램
                NodeList signature = fastElan.getElementsByTagName("signature");// 확인된 문자열
                NodeList dateitme = fastElan.getElementsByTagName("datetime");// 데이터베이스 동록 날짜
                NodeList last = fastElan.getElementsByTagName("last"); // 데이터베이스 수정 날짜.


                final Node Node_identifier = identifier.item(0).getChildNodes().item(0);
                final Node Node_prefix = prefix.item(0).getChildNodes().item(0);
                final Node Node_orgranization = orgranization.item(0).getChildNodes().item(0);
                final Node Node_description = description.item(0).getChildNodes().item(0);
                final Node Node_execution = execution.item(0).getChildNodes().item(0);
                final Node Node_content = content.item(0).getChildNodes().item(0);
                final Node Node_opewnith = opewnith.item(0).getChildNodes().item(0);
                final Node Node_signature = signature.item(0).getChildNodes().item(0);
                final Node Node_dateitme = dateitme.item(0).getChildNodes().item(0);

                //Node_identifier 이랑 Node_prefix null  일경우
                if (Node_identifier == null || Node_prefix == null) {
                    // 풍션메시지 띄워줍니다.
                    Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                    //라턴 처리
                    return;
                //
                } else { //
                    //
                    final ListView list = findViewById(R.id.ListView1);
                    Str_identifier = "" + Node_identifier.getNodeValue() + "";
                    arraylist_identifier.add(Str_identifier);

                    final ArrayAdapter<String> arrayAdapter_identifier = new ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, arraylist_identifier);
                    Str_prefix = ""+ Node_prefix.getNodeValue() +"";

                    arraylist_prefix.add(Str_prefix);
                    final ArrayAdapter<String> arrayAdapter_prefix = new ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, arraylist_prefix);
                    if(Node_execution == null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_execution = "";
                        arraylist_execution.add(Str_execution);
                        final ArrayAdapter<String> arrayAdapter_execution = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_execution);

                    } else {
                        Str_execution = "실행정보 : "+ Node_execution.getNodeValue() +"";
                        arraylist_execution.add(Str_execution);
                        final ArrayAdapter<String> arrayAdapter_execution = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_execution);

                    }

                    if(Node_description == null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_description = "";
                        arraylist_description.add(Str_description);
                        final ArrayAdapter<String> arrayAdapter_description = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_description);

                    } else {
                        Str_description = "간단 요약설명 : "+ Node_description.getNodeValue() +"";
                        arraylist_description.add(Str_description);
                        final ArrayAdapter<String> arrayAdapter_description = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_description);

                    }

                    if(Node_orgranization == null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_orgranization = "";
                        arraylist_orgranization.add(Str_orgranization);
                        final ArrayAdapter<String> arrayAdapter_orgranization = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_orgranization);

                    } else {
                        Str_orgranization = "회사 : "+ Node_orgranization.getNodeValue() +"";
                        arraylist_orgranization.add(Str_orgranization);
                        final ArrayAdapter<String> arrayAdapter_orgranization = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_orgranization);

                    }
                    if(Node_content == null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_content = "";
                        arraylist_orgranization.add(Str_content);
                        final ArrayAdapter<String> arrayAdapter_content = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_content);

                    } else {
                        Str_content = "파일 상세정보 : "+ Node_content.getNodeValue() +"";
                        arraylist_content.add(Str_content);
                        final ArrayAdapter<String> arrayAdapter_content = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_content);

                    }
                    if(Node_opewnith == null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_opewnith = "";
                        arraylist_opewnith.add(Str_opewnith);
                        final ArrayAdapter<String> arrayAdapter_opewnith = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_opewnith);

                    } else {
                        Str_opewnith = "여는 프로그램 : "+ Node_opewnith.getNodeValue() +"";
                        arraylist_opewnith.add(Str_opewnith);
                        final ArrayAdapter<String> arrayAdapter_opewnith = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_opewnith);
                    }
                    if(Node_signature == null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_signature = "";
                        arraylist_signature.add(Str_signature);
                        final ArrayAdapter<String> arrayAdapter_signature = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_signature);

                    } else {
                        Str_signature = "확인된 문자열 : "+ Node_signature.getNodeValue() +"";
                        arraylist_signature.add(Str_signature);
                        final ArrayAdapter<String> arrayAdapter_signature = new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_signature);

                    }
                    if(Node_dateitme== null) {
                        //         Toast.makeText(getBaseContext(), "찾으신 데이터베이스가 존재하지 않습니다. ", Toast.LENGTH_SHORT).show();
                        Str_dateitme= "";
                        arraylist_dateitme.add(Str_dateitme);
                        final ArrayAdapter<String> arrayAdapter_dateitme= new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_dateitme);
                    } else {
                        Str_dateitme= "데이터베이스 동록 날짜 : "+ Node_dateitme.getNodeValue() +"";
                        arraylist_dateitme.add(Str_dateitme);
                        final ArrayAdapter<String> arrayAdapter_dateitme= new ArrayAdapter<String>(
                                this, android.R.layout.simple_list_item_1, arraylist_dateitme);
                    }
                    list.setAdapter(arrayAdapter_identifier);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           // Toast.makeText(getBaseContext(), arraylist_identifier.get(position), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
                            intent.putExtra("identifier", arraylist_identifier.get(position) + "");
                            intent.putExtra("prefix", arraylist_prefix.get(position) + "\n");
                            intent.putExtra("description", arraylist_description.get(position) + "\n");
                            intent.putExtra("orgranization", arraylist_orgranization.get(position) + "\r\n");
                            intent.putExtra("execution", arraylist_execution.get(position) + "\r\n");
                            intent.putExtra("content", arraylist_content.get(position) + "\r\n");
                            intent.putExtra("opewnith", arraylist_opewnith.get(position) + "\r\n");
                            intent.putExtra("signature", arraylist_signature.get(position) + "\r\n");
                            intent.putExtra("dateitme", arraylist_dateitme.get(position) + "\r\n");
                            startActivity(intent);


                        }
                    });
                }
            }
    }
}
