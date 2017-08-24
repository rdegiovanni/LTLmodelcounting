#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM.txt

echo "Domain and BC1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1.txt

echo "Domain and BC1 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-G1.txt

echo "Domain and BC1 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-G2.txt


echo "Domain and BC2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC2.txt

echo "Domain and BC2 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC2-G1.txt

echo "Domain and BC2 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC2-G2.txt


echo "Domain and BC3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC3.txt

echo "Domain and BC3 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC3-G1.txt

echo "Domain and BC3 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC3-G2.txt


echo "Domain and BC1-BC2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC2.txt

echo "Domain and BC1-BC2 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC2-G1.txt

echo "Domain and BC1-BC2 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC2-G2.txt


echo "Domain and BC1-BC3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC3.txt

echo "Domain and BC1-BC3 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC3-G1.txt

echo "Domain and BC1-BC3 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC3-G2.txt


echo "Domain and BC2-BC3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC2-BC3.txt

echo "Domain and BC2-BC3 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC2-BC3-G1.txt

echo "Domain and BC2-BC3 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC2-BC3-G2.txt


echo "Domain and BC1-BC2-BC3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC2-BC3.txt

echo "Domain and BC1-BC2-BC3 and G1 and not G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="G((p && ! l) -> m)" -g="! G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC2-BC3-G1.txt

echo "Domain and BC1-BC2-BC3 and not G1 and G2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! G((p && ! l) -> m)" -g="G((! p) -> ((! m) U l))" -bc="F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -bc="F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -bc="F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-BC1-BC2-BC3-G2.txt


echo "Domain and no BCs"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G(l -> F(! l))" -g="! F ((l && G((((l && m) && X ((l || ((! m && p) || (m && ! p))))) || (l || ((! m && p) || (m && ! p)))))))" -g="! F (((l && m) && X ((! l && ((! m && p) || (m && ! p))))))" -g="! F ((! l && ((! m && p) || (m && ! p))))" -alph="[p,m,l]" -all -k=100 -out=case-studies-allcomb/atm/ATM-DOM-noBCs.txt

