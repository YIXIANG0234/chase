-- 单行注释，lua是动态类型语言

-- 第一行代码
print("hello world!")

-- 全局变量
globalVar = 10

-- 局部变量
local localVar = 20
print(globalVar * localVar)

-- 数据类型，lua将nil/false看作false，其他值都当作true
print(type("hello"))
print(type(globalVar))
print(type(print))
print(type(true))
print(type(nil))

-- table类型变量的定义，可以使用数字或者字符串作为索引访问表中的元素
-- 另外lua下标不同于其他语言，是从1开始而不是从0开始的
local tab = {"java", "c", "lua", "python"}
tab["this"] = "Lua"
for k, v in pairs(tab) do
-- lua使用..进行字符串拼接
    print(k .. ":" .. v)
end

-- 函数
function add(a, b)
    print(a + b)
end
-- 调用函数
print(add(5, 10))

-- while循环
while(localVar < 25)
do
print("localVar".. "=" .. localVar)
localVar = localVar + 1
end

-- for循环
for i=1, 5
do
print("i" .. "=" .. i)
end

-- repeat...until，先执行一次循环体，util中的条件为true时停止，为false时继续下一轮循环
repeat
print("globalVar" .. "=" .. globalVar)
until(globalVar>=10)

-- if语句
if (localVar > 10)
then
print("localVar > 10，localVar的值为：" .. localVar)
end

-- if-else语句
if (localVar < 10)
then
print("localVar < 10，localVar的值为：" .. localVar)
else
print("localVar命中了else语句块")
end

-- if-elseif-else语句
function getLevel (score)
    if (score >= 90)
    then
        return "A+"
    elseif (score>=80)
    then
        return "A"
    elseif (score>=75)
    then
        return "B"
    elseif (score >= 70)
    then
        return "B-"
    elseif (score>=60)
    then
        return "C"
    else
        return "D"
    end
end

print("95 level is : " .. getLevel(95))
print("86 level is : " .. getLevel(86))
print("78 level is : " .. getLevel(78))
print("73 level is : " .. getLevel(73))
print("65 level is : " .. getLevel(65))
print("55 level is : " .. getLevel(55))


-- 使用function关键字定义函数时，默认是全局函数，要定义局部函数，需要额外使用local关键字
local function localFun()
    print("this is local function")
end

localFun()

-- 可以将函数作为另一个函数的参数传入
function myPrint(param)
    print("myPrint: " .. param)
end

function loopNumber(threshold, supplier)
    for i=1, threshold
    do
        supplier(i)
    end
end

-- 调用函数loopNumber，并将myPrint函数作为参数传入
loopNumber(3, myPrint)

-- lua中，函数可以一次性返回多个值，定义多个返回值的函数
function multiResult(a)
    return a+10, "对a进行字符拼接："..a
end

print(multiResult(99))

-- 可变参数
function sum(...)
    local result=0
    for k,v in pairs{...}
    do
        result = result+v
    end
    return result
end

print(sum(1, 2, 3, 4, 5))

-- 比较特殊的运算符 # 用于返回字符串或者表的长度
print("hello的长度为：" .. #"hello")
print("tab表的长度为：" .. #tab)

-- lua中的数组，即前面有说到的表，索引从1开始
local myArray = {10, 20, 30, 40, 50}
for i=1, #myArray
do
    myPrint(i)
end

-- 迭代器