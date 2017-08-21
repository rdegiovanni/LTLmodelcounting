#!/bin/bash
echo "Domain"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM.txt

echo "Domain and O1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-O1.txt

echo "Domain and O1 and G1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G(s))" -g="G(m -> F(o))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-O1-G1.txt

echo "Domain and O2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G (b))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-O2.txt

echo "Domain and O2 and G1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G (b))" -g="G(m -> F(o))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-O2-G1.txt

echo "Domain and O3"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G (l))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-O3.txt

echo "Domain and O3 and G1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F (m && G (l))" -g="G(m -> F(o))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-O3-G1.txt

echo "Domain and SO1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F(m && G(! f))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-SO1.txt

echo "Domain and SO1 and G1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F(m && G(! f))" -g="G(m -> F(o))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-SO1-G1.txt

echo "Domain and SO2"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F(m && G (! g))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-SO2.txt

echo "Domain and SO2 and G1"
java -Xmx8g -Djava.library.path=/usr/local/lib -cp bin/.:lib/* main.Main -ltl="G((s || l || b) -> ! o) && G((! f && ! g) => l)" -alph="[m,o,f,s,l,b,g]" -bc="F(m && G (! g))" -g="G(m -> F(o))" -all -k=100 -out=case-studies/obstacles/las/LAS-DOM-SO2-G1.txt

