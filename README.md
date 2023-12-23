## Part 1
We run the program with inputs (4,7,9) and after profiling, we realize that `temp` function takes most of processing time.

//picture 1
//picture 2

After further investigations, it seems like we spend significant time in `add` function for our dynamic array.

//picture 3

So we change the code to allocate ram in the beginning:

```java
ArrayList a = new ArrayList(10000 * 20000);
```

//picture 4
//picture 5

We use a simple fixed size array for further improvement.

```java
int[] a = new int[10000 * 20000];
int index = 0;
for (int i = 0; i < 10000; i++)
{
    for (int j = 0; j < 20000; j++) {
        a[index] = i + j;
        index++;
    }
}
```

//picture 6
//picture 7

As you can see in the pictures, cpu time has improved significantly.