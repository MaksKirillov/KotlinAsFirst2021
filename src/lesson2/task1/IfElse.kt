@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.*


// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when {
        age % 10 == 0 || age % 10 in 5..9 || age % 100 in 11..19 -> "$age лет"
        age % 10 == 1 -> "$age год"
        else -> "$age года"
    }


/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s = ((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2
    val s1 = t1 * v1
    val s2 = t2 * v2
    return when {
        s1 >= s -> s / v1
        s1 + s2 >= s -> (s - s1) / v2 + t1
        else -> (s - s1 - s2) / v3 + t1 + t2
    }

}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when {
    (kingX == rookX1 && kingY == rookY2) || (kingX == rookX2 && kingY == rookY1) -> 3
    (kingX == rookX2 && kingY != rookY1) || (kingY == rookY2 && kingX != rookX1) -> 2
    (kingX == rookX1 && kingY != rookY2) || (kingY == rookY1 && kingX != rookX2) -> 1
    else -> 0
}

/* when {
    (kingX == rookX1 && kingY == rookY2) || (kingX == rookX2 && kingY == rookY1) -> 3
    kingX == rookX1 && kingX == rookX2 && kingY in rookY1..rookY2 -> 3
    kingY == rookY1 && kingY == rookY2 && kingX in rookX1..rookX2 -> 3
    kingX == rookX1 && kingX == rookX2 && rookY1 in kingY..rookY2 -> 1
    kingX == rookX1 && kingX == rookX2 && rookY2 in kingY..rookY1 -> 2
    kingY == rookY1 && kingY == rookY2 && rookX1 in kingX..rookX2 -> 1
    kingY == rookY1 && kingY == rookY2 && rookX2 in kingX..rookX1 -> 2
    kingX == rookX1 && kingY != rookY2 -> 1
    kingY == rookY1 && kingX != rookX2 -> 1
    kingX == rookX2 && kingY != rookY1 -> 2
    kingY == rookY2 && kingX != rookX1 -> 2
    else -> 0
} */ //ладьи могут загораживать друг друга


/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int = when {
    (kingX == rookX || kingY == rookY) && abs(kingX - bishopX) == abs(kingY - bishopY) -> 3
    kingX != rookX && kingY != rookY && abs(kingX - bishopX) == abs(kingY - bishopY) -> 2
    (kingX == rookX || kingY == rookY) && abs(kingX - bishopX) != abs(kingY - bishopY) -> 1
    else -> 0
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val big = maxOf(a, b, c)
    return when {
        a >= b + c || b >= a + c || c >= b + a -> -1
        sqr(a) + sqr(b) < sqr(big) || sqr(c) + sqr(a) < sqr(big) || sqr(b) + sqr(c) < sqr(big) -> 2
        sqr(a) + sqr(b) == sqr(big) || sqr(c) + sqr(a) == sqr(big) || sqr(b) + sqr(c) == sqr(big) -> 1
        else -> 0
    }
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    (d < a) || (b < c) -> -1
    c in a..b && b <= d -> b - c
    a in c..d && d <= b -> d - a
    a <= c && d <= b -> d - c
    c <= a && b <= d -> b - a
    else -> 0
}
