
import com.adropofliquid.SeedPhraseBip39;
import com.adropofliquid.secretshare.SecretSplit;
import org.junit.jupiter.api.Test;
import org.mitre.secretsharing.Part;
import org.mitre.secretsharing.Secrets;
import org.mitre.secretsharing.codec.PartFormats;


import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.*;


class SplitTest {


	@Test
	void testAll(){
		String[] splits = SecretSplit.splitSecretBase64("approaches", 3, 2);

		for(int i = 0; i < splits.length; i++){
			for(int j = 0; j < splits.length; j++){
				if(j != i) {
					String[] s = new String[]{splits[i], splits[j]};
					String secret = SecretSplit.getSecret(s);
//					assertThat(secret).isEqualTo("approaches");
					System.out.println(secret);
				}
			}
		}
	}

	@Test
	void testSecret(){
		byte[] secret = "approaches".getBytes(StandardCharsets.UTF_8);
		Part[] parts = Secrets.split(secret, 200,2, new SecureRandom());

		String[] stringParts = new String[parts.length];

		for(int s = 0; s < parts.length; s++)
			stringParts[s] = PartFormats.currentStringFormat().format(parts[s]);


		for(int i = 0; i < stringParts.length; i++){
			for(int j = 0; j < stringParts.length; j++){
				if(j != i) {
					Part[] p = new Part[]{PartFormats.parse(stringParts[i]), PartFormats.parse(stringParts[j])};
					byte[] ss = Secrets.join(p);
					String secretString = new String(ss, StandardCharsets.UTF_8);
					assertThat(secretString).isEqualTo("approaches");

//					System.out.println(secretString); I just like printing to the console init?
				}
			}
		}
	}

}
