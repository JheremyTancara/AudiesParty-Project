			********************************************************************************************************
					Test Greegy & String matching
********************************************************************************************************
Q1. Why do we need to do a formal proof for a greedy algorithm

Porque hacer una demostración formal para un algoritmo Greedy es necesario para poder garantizar que siempre produzca la solución óptima o, al menos, una solución aceptable según el problema específico. Y asi tener mejoras o tener el algoritmo mas optimo, porque pueden existir muchos algoritmos greedys pero solo uno optimo, tener la mejor opción en cada paso, sin importar el orden de los elementos de entrada. Esto es crucial para asegurar la corrección y eficacia del algoritmo en una variedad de casos y para evitar posibles errores o malentendidos en su implementación y aplicación.


********************************************************************************************************
Q2. When can the Rabin-Karp tme complexity can be worse or the same as the naive algorithm
El tiempo de complejidad del algoritmo de Rabin-Karp puede empeorar o ser igual que el del algoritmo ingenuo en casos donde hay muchas coincidencias entre el patrón y el texto. Esto puede ocurrir cuando la función de hash utilizada no es lo suficientemente buena y produce colisiones frecuentes en tales casos, el algoritmo Rabin-Karp podría requerir un tiempo adicional para manejar estas
El algoritmo de Rabin-Karp tiene una complejidad de tiempo promedio de O(n+m), donde n es la longitud del texto y m es la longitud del patrón, lo que lo hace más eficiente en la mayoría de los casos en comparación con el algoritmo ingenuo, que tiene una complejidad de tiempo de O((n-m+1)*m).


********************************************************************************************************
Q3. Given a P, what is the highest value we can get in the prefix function and why?
Para La pregunta el valor más alto que podemos obtener en la función de prefijo para una cadena P es n - 1, donde n es la longitud de la cadena P. Esto se debe a que en el caso de la función de prefijo, estamos comparando un prefijo de la cadena con un sufijo, y el valor máximo que podemos obtener es cuando el prefijo es igual al sufijo, pero excluyendo la cadena completa.

********************************************************************************************************
Q4. Coco's theme party
As we know, at Coco's theme pary....

    Primero apuntemos los puntos mas importantes:
    * En coco's theme party cada usuario tenia su nivel actual de felicidad, la fiesta empezo y todos 	los invitados estan sentados en linea, coco tiene una idea para incrementar la felicidad de los 	invitados, ella decide darle a cada uno de ellos algunos souvenirs, Coco sabe que el invitado x 	is next to a happier gues, Y, guest Y will only increase their happiness, si ellos reciben mas 	souvenirs que el invitado guest
    
    Sample Input1:
    4
    2 2 10 5
    
    Sample Output1:
    6
    
    Sample Input2:
    3
    10 3 4
    
    Sample Output2:
    4
    
    Sample Input3:
    3
    2 5 10
    
    Sample Output3:
    6
    
    Input: Un numero n, seguido por n numeros que representa el nivel felicidad de cada invitado
    Output: La cantidad minima de souvenirs that coco should hand out
    a. Identifica el caso Greegy
    El caso geedy hace un verificacion del nivel de felicidad con la izquierda o la derecha, si es el 	primer elemento hace verificacion solo con la derecha, si es el ultimo elemento hace 		   verificacion solo con la izquierda y si es mayor a los niveles de felicidad de los 		costados solo se subo uno 1 souvenir y si es menor verifica cual elemento es el mayor y suma 	el souvenir de ese elemento mas 1.
    
    b. Identifica la subestructura optima
    La subestructura optima es SO - Solucion Optima, G - General, L1 - Local 1
    La formula para interactuar sobre la solucion optima es SO(G) = SO(L1) U SO(G - L1)
    La subestructura optima es cuando el caso pueda contemplar numeros mayores y menores de los 		elementos a nivel de felicidad
    
    c. Implementa tu solucion
    Ya subi el codigo correctanebte
    
    d. ¿Cual es el tiempo de complejidad de mi solucion?
    El tiempo de complejidad de mi solucion es O (n) que podria ser la comparacion entre los niveles 	de felicidad
