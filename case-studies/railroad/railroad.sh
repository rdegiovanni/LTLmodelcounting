#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((t && X(c)) || (! t && X(! c))) && G(X(b) -> (a && g))" -alph="[a,b,c,g,t]" -all -k=20 -out=case-studies/railroad/RAILROAD-DOM.txt

echo "Domain and BC1"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((t && X(c)) || (! t && X(! c))) && G(X(b) -> (a && g))" -bc="(((! b && (g && t)) || (b && ((! g && c) || (g && (t || c))))))" -alph="[a,b,c,g,t]" -k=$K -out=case-studies/railroad/RAILROAD-DOM-BC1.txt
done

echo "Domain and BC1 and G1 and not G2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((t && X(c)) || (! t && X(! c))) && G(X(b) -> (a && g))" -g="G (c -> ! b)" -g="! G (t -> ! g)" -bc="(((! b && (g && t)) || (b && ((! g && c) || (g && (t || c))))))" -alph="[a,b,c,g,t]" -k=$K -out=case-studies/railroad/RAILROAD-DOM-BC1-G1.txt
done

echo "Domain and BC1 and not G1 and G2"
for K in {1..20}
do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((t && X(c)) || (! t && X(! c))) && G(X(b) -> (a && g))" -g="! G (c -> ! b)" -g="G (t -> ! g)" -bc="(((! b && (g && t)) || (b && ((! g && c) || (g && (t || c))))))" -alph="[a,b,c,g,t]" -k=$K -out=case-studies/railroad/RAILROAD-DOM-BC1-G2.txt
done
