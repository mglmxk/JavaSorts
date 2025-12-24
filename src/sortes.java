import java.util.Arrays;

public class sortes {
    public static void main(String[] args) {
        int[] sizes = {2000, 5000, 10000, 22000, 53000, 100000, 220000, 330000, 510000};

        for (int size : sizes) {
            System.out.println("Размер массива: " + size);

            int[] original = new int[size];
            for (int i = 0; i < size; i++) original[i] = (int)(Math.random() * 100001);

            // QuickSort медиана
            long sum = 0;
            for (int run = 1; run <= 3; run++) {
                int[] arr = Arrays.copyOf(original, original.length);
                long start = System.nanoTime();
                quickSortMedian(arr, 0, arr.length - 1);
                long time = System.nanoTime() - start;
                sum += time;
                System.out.println("QuickSort медиана, запуск " + run + ": " + time/1_000_000.0 + " мс");
            }
            System.out.println("Среднее: " + sum/3/1_000_000.0 + " мс\n");

            // Шейкер
            sum = 0;
            for (int run = 1; run <= 3; run++) {
                int[] arr = Arrays.copyOf(original, original.length);
                long start = System.nanoTime();
                shakerSort(arr);
                long time = System.nanoTime() - start;
                sum += time;
                System.out.println("Шейкер, запуск " + run + ": " + time/1_000_000.0 + " мс");
            }
            System.out.println("Среднее: " + sum/3/1_000_000.0 + " мс\n");

            // Шелл
            sum = 0;
            for (int run = 1; run <= 3; run++) {
                int[] arr = Arrays.copyOf(original, original.length);
                long start = System.nanoTime();
                shellSort(arr);
                long time = System.nanoTime() - start;
                sum += time;
                System.out.println("Шелл, запуск " + run + ": " + time/1_000_000.0 + " мс");
            }
            System.out.println("Среднее: " + sum/3/1_000_000.0 + " мс");
            System.out.println("------------------------------");
        }
    }

    static void quickSortMedian(int[] a, int low, int high) {
        if (low < high) {
            int p = partitionMedian(a, low, high);
            quickSortMedian(a, low, p);
            quickSortMedian(a, p+1, high);
        }
    }

    static int partitionMedian(int[] a, int low, int high) {
        int mid = (low+high)/2;
        int pivot = median(a[low], a[mid], a[high]);
        int i = low-1, j = high+1;
        while (true) {
            do i++; while (a[i]<pivot);
            do j--; while (a[j]>pivot);
            if (i>=j) return j;
            int t=a[i]; a[i]=a[j]; a[j]=t;
        }
    }

    static int median(int a,int b,int c) {
        if ((a-b)*(c-a)>=0) return a;
        if ((b-a)*(c-b)>=0) return b;
        return c;
    }

    static void shakerSort(int[] a) {
        int left=0, right=a.length-1; boolean swapped=true;
        while(swapped){
            swapped=false;
            for(int i=left;i<right;i++){if(a[i]>a[i+1]){int t=a[i];a[i]=a[i+1];a[i+1]=t;swapped=true;}}
            right--;
            for(int i=right;i>left;i--){if(a[i]<a[i-1]){int t=a[i];a[i]=a[i-1];a[i-1]=t;swapped=true;}}
            left++;
        }
    }

    static void shellSort(int[] a){
        for(int gap=a.length/2;gap>0;gap/=2){
            for(int i=gap;i<a.length;i++){
                int temp=a[i], j=i;
                while(j>=gap && a[j-gap]>temp){a[j]=a[j-gap]; j-=gap;}
                a[j]=temp;
            }
        }
    }
}