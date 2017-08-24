#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM.txt

echo "Domain and BC1"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="TRUE" -bc="F (((! a && ((! c && ((! h && (n || i)) || (h && (! n && i)))) || (c && ((! d && ((! h && (n || i)) || (h && ((! m && (! n && i)) || (m && (n || i)))))) || (d && (! h || ! n)))))) || (a && ((! c && ((! h && (! m || (n || i))) || (h && (! m || (! n && i))))) || (c && ((! d && (! m || (n || i))) || (d && (! h || (! m || ! n)))))))))" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM-BC1.txt
#done

echo "Domain and BC1 and G1"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(a -> m)" -bc="F (((! a && ((! c && ((! h && (n || i)) || (h && (! n && i)))) || (c && ((! d && ((! h && (n || i)) || (h && ((! m && (! n && i)) || (m && (n || i)))))) || (d && (! h || ! n)))))) || (a && ((! c && ((! h && (! m || (n || i))) || (h && (! m || (! n && i))))) || (c && ((! d && (! m || (n || i))) || (d && (! h || (! m || ! n)))))))))" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM-BC1-G1.txt
#done

echo "Domain and BC1 and G2"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((c && m && n) -> d)" -bc="F (((! a && ((! c && ((! h && (n || i)) || (h && (! n && i)))) || (c && ((! d && ((! h && (n || i)) || (h && ((! m && (! n && i)) || (m && (n || i)))))) || (d && (! h || ! n)))))) || (a && ((! c && ((! h && (! m || (n || i))) || (h && (! m || (! n && i))))) || (c && ((! d && (! m || (n || i))) || (d && (! h || (! m || ! n)))))))))" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM-BC1-G2.txt
#done

echo "Domain and BC1 and G3"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((c && (! n)) -> (! d)" -bc="F (((! a && ((! c && ((! h && (n || i)) || (h && (! n && i)))) || (c && ((! d && ((! h && (n || i)) || (h && ((! m && (! n && i)) || (m && (n || i)))))) || (d && (! h || ! n)))))) || (a && ((! c && ((! h && (! m || (n || i))) || (h && (! m || (! n && i))))) || (c && ((! d && (! m || (n || i))) || (d && (! h || (! m || ! n)))))))))" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM-BC1-G3.txt
#done

echo "Domain and BC1 and G4"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((! h) -> (! n))" -bc="F (((! a && ((! c && ((! h && (n || i)) || (h && (! n && i)))) || (c && ((! d && ((! h && (n || i)) || (h && ((! m && (! n && i)) || (m && (n || i)))))) || (d && (! h || ! n)))))) || (a && ((! c && ((! h && (! m || (n || i))) || (h && (! m || (! n && i))))) || (c && ((! d && (! m || (n || i))) || (d && (! h || (! m || ! n)))))))))" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM-BC1-G4.txt
#done

echo "Domain and BC1 and G5"
#for K in {1..20}
#do
	java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((! n) -> (! i))" -bc="F (((! a && ((! c && ((! h && (n || i)) || (h && (! n && i)))) || (c && ((! d && ((! h && (n || i)) || (h && ((! m && (! n && i)) || (m && (n || i)))))) || (d && (! h || ! n)))))) || (a && ((! c && ((! h && (! m || (n || i))) || (h && (! m || (! n && i))))) || (c && ((! d && (! m || (n || i))) || (d && (! h || (! m || ! n)))))))))" -alph="[a,m,c,n,d,h,i]" -all -k=1000 -out=case-studies/las/LAS-DOM-BC1-G5.txt
#done
