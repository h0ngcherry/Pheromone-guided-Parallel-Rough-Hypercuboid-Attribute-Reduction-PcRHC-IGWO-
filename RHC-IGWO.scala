package com.hongcherry
import org.apache.spark.sql.{SparkSession, DataFrame}

import org.apache.spark
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn
import scala.util.Random
import scala.math._

object RHC_BGWO {
  def convertToTuple(s: String): ArrayBuffer[(String, (Double, Double))] = {
    val str_split: Array[String] = s.split(",")
    val tp_res = new ArrayBuffer[(String, (Double, Double))]()
    for (i <- 0 until str_split.length - 1) {
      tp_res.append((i.toString + "-" + str_split(str_split.length - 1), (str_split(i).toDouble, str_split(i).toDouble)))
    }
    tp_res
  }
  def convertToHyperMatrix(s: String, LUmap: Map[Int, ArrayBuffer[(Double, Double, String)]])=                                                                                                                                             {
    val str_sp = s.split(",")
    var hyperMatrix = Map[Int, ArrayBuffer[String]]()
    for (i <- 0 until str_sp.length-1)
    {
      for (a <- LUmap(i))
      {
        if (a._1 <= str_sp(i).toDouble && str_sp(i).toDouble <= a._2)
        {
          if (hyperMatrix.keySet.contains(i))
          {
            hyperMatrix(i).append(a._3)
          }
          else{
            hyperMatrix += (i -> ArrayBuffer(a._3))
          }
        }
      }
    }
    hyperMatrix
  }
  def fit_rele(x:Map[Int,ArrayBuffer[String]]):ArrayBuffer[(Int,Int)] = {
    val relevance = new ArrayBuffer[(Int,Int)]()
    for (i <- x.keySet)
    {
      relevance.append((i,1-Math.min(1,x(i).size-1)))
    }
    relevance
  }
  def fit_dep(x: Map[Int, ArrayBuffer[String]], Sset: Array[Array[Int]])
  : ArrayBuffer[(Int, Int)] = {
    val dependency = new ArrayBuffer[(Int, Int)]()
    for (i <- Sset.indices) {
      var first = 1
      var tem = new ArrayBuffer[String]()
      if (Sset(i).sum > 0) {
        for (j <- Sset(i).indices) {
          if (Sset(i)(j) == 1) {
            if (first == 1) {
              tem = x(j).clone()
              first = 0
            }
            else {
              tem = tem.intersect(x(j))
            }
          }
        }
        dependency.append((i, 1 - Math.min(1, tem.size - 1)))
      } else {
        dependency.append((i, 0))
      }
    }
    dependency
  }
  def fit_sig(x:Map[Int,ArrayBuffer[String]]):ArrayBuffer[(String,Double)] = {
    val significance = new ArrayBuffer[(String,Double)]()
    for (i <- 0 until x.size-1)
    {
      val Vi = Math.min(1,x(i).size-1)
      for (j <- i+1 until x.size)
      {
        val Vj = Math.min(1,x(j).size-1)
        val Vij = Math.min(1, (x(i).intersect(x(j))).size - 1)
        val signum = Vj.toDouble - Vij.toDouble + Vi.toDouble - Vij.toDouble
        significance.append((i + "-" + j, signum))
      }
    }
    significance
  }
  def score(array: Array[Int], rele: Map[Int, Double], depen: Double, sig: Double, omega: Double, lambda: Double): Double = {
    var fitScore = 0.0
    if (array.sum > 0) {
      for (i <- array.indices) {
        if (array(i) == 1) {
          fitScore = fitScore + rele(i)
        }
      }
      fitScore = fitScore / array.sum.toDouble
      println(fitScore)
    }
    val res = omega * fitScore + (1.0 - omega) * lambda * depen + (1.0 - omega) * (1.0 - lambda) * sig
    if (res > 1) {
      println("position:" + array.mkString("-"))
    }
    fitScore
  }
