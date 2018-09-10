/**
 * @author 李飞翔
 * @ SSLHttpsUtils.java
 * 时间：2016-1-11 下午8:03:05
 *
 */

package com.example.dell.baseproject.https;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author 李飞翔
 * @name SSLHttpsUtils.java
 * @data 2016-1-11 下午8:03:05
 * @TODO
 */
public class SSLHttpsUtils extends SSLSocketFactory {

    /**
     * @param truststore
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     */
    public SSLHttpsUtils(KeyStore truststore) throws NoSuchAlgorithmException,
            KeyManagementException, KeyStoreException,
            UnrecoverableKeyException {
        super(truststore);
        initSSLContext();
    }

    TrustManager manager = new X509TrustManager() {

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
            // TODO Auto-generated method stub

        }
    };
    private SSLContext context = SSLContext.getInstance("TLS");

    private void initSSLContext() {
        try {
            // 既然是跳过认证，我们把没有的都填null，此时发现第二个参数是一个数组，那么意思就是我们可以放多个证书认证；
            context.init(null, new TrustManager[] { manager }, null);
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static DefaultHttpClient getNewHttpClient() {
        try {
            // 查看API这里可以得到一个默认的Type.
            KeyStore truststore = KeyStore.getInstance(KeyStore
                    .getDefaultType());
            // 这里发现需要一个KeyStore，那么我们就在上面New一个KeyStore,这是一个密钥库，查看API发现能直接getInstance得到对象;
            SSLSocketFactory factory = new SSLHttpsUtils(truststore);
            // 这里就是我们最需要的也是最关键的一步，设置主机认证,通过API发现有一个常量就是允许所有认证通过。
            factory.setHostnameVerifier(ALLOW_ALL_HOSTNAME_VERIFIER);

            // 实现Httpprams的子类
            HttpParams params = new BasicHttpParams();
            //通过Http适配器设置必要参数，现在通用HTTP1.1协议,和UTF-8字符。
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            //通过适配器设置连接参数,等待时间和，连接时间
            HttpConnectionParams.setSoTimeout(params, 10000);
            HttpConnectionParams.setConnectionTimeout(params, 5000);

            // 同样New出来，查看API，需要我们注册一个计划,来封装协议细节
            SchemeRegistry schreg = new SchemeRegistry();
            // 最后一个参数是端口设置，Https常用的端口就是443。
            schreg.register(new Scheme("https", factory, 443));
            // 既然是工具类，这里也把http协议加上，中间的协议工厂我们就用简单的PlainSocketFactory，这里可以通过API查看到，端口就用常用的80端口（默认）
            schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

            // ClientConnectionManager是一个借口，就实现他的子类,同样需要2个参数，第一个我们熟悉，第二个就是让我们自定义自己的一套方案协议，继续在上面一步一步完成；
            ClientConnectionManager conman = new SingleClientConnManager(
                    params, schreg);

            // 返回我们需要的一个默认Httpclient，为了把之前做的关联起来，就new最多参数的构造函数，需要2个参数，Httpparams是我们熟悉的，
            // 发现ClientConnectionManager不太熟悉，通过API发现这是客服端连接管理者,既然这样，就在上面一步一步完成。
            return new DefaultHttpClient(conman, params);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return new DefaultHttpClient();
    }
}
