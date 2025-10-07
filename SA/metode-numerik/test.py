def bisection_method(a, b, tol):
    if f(a) * f(b) >= 0:
        print("Penentuan a dan b salah")
        return None
    c=(a+b)/2
    print("a\tb\tfx(a)\tfx(b)\tc") 
a_tmp = a
b_tmp = b
while abs(fx (a_tmp)*fx (b_tmp)) > 0.000001:
    c_tmp = (a_tmp+b_tmp)/2
    print(round (a_tmp,3),"\t", round (b_tmp,3),"\t", round (fx(a_tmp),3),"\t", round (fx(b_tmp),3),"\t",c_tmp) 
    if (fx(a_tmp)*fx(c_tmp) <0.0):
        b_tmp=c_tmp
    else:
        a_tmp=c_tmp
print("\n Akar persamaan adalah ", (c))