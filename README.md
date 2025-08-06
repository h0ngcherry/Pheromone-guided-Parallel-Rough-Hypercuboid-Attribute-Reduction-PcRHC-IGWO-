# 🧬 Pheromone-guided Parallel Rough Hypercuboid Attribute Reduction (PcRHC-IGWO)

This repository contains the implementation of the **PcRHC-IGWO algorithm**, proposed in the paper published in *Applied Soft Computing Journal* (2024):

> **"Pheromone-guided parallel rough hypercuboid attribute reduction algorithm"**  
> Authors: Weiping Ding, Hongcheng Yao, et al.  
> DOI: [10.1016/j.asoc.2024.111479](https://doi.org/10.1016/j.asoc.2024.111479)

---

## 🔍 Overview

**PcRHC-IGWO** is a scalable, Spark-based metaheuristic attribute reduction algorithm for high-dimensional data. It integrates:

- **Rough Hypercuboid Model** for uncertain data modeling  
- **Improved Binary Grey Wolf Optimizer (IGWO)** enhanced by:  
  - ✨ Pheromone mechanism (from ACO)  
  - 🔀 Mutation operations (from GA)  
- **Apache Spark** for parallelism

It is especially effective for large-scale feature selection tasks in distributed environments.

---

## 🏗️ Project Structure

```
.
├── src/
│   └── RHC_BGWO.scala           # Core implementation of PcRHC-IGWO
├── dataset/
│   └── BASEHOCK_sampled_pca.csv  # Sample dataset
├── README.md                    # Project documentation
└── LICENSE                      # License file (e.g., Apache 2.0 or MIT)
```

---

## ⚙️ Requirements

- Scala 2.11.12  
- Spark 2.4.5  
- Hadoop 2.7.1  
- Java 8+

---

## 🚀 Usage

### 1. Clone the repository

```bash
git clone https://github.com/your-username/PcRHC-IGWO.git
cd PcRHC-IGWO
```

### 2. Compile and Submit Job

```bash
spark-submit \
  --class com.hongcherry.RHC_BGWO \
  --master local[*] \
  target/scala-2.11/pc-rhc-igwo_2.11-1.0.jar
```

Make sure the dataset path (`dataset/BASEHOCK_sampled_pca.csv`) is correctly placed or modified in code.

---

## 🧪 Benchmark & Results

- Classification accuracy improved up to **92.86%** on schizophrenia datasets  
- Processing time reduced by **85.71%** on large-scale datasets  
- Outperforms traditional methods in relevance, dependence, and significance  

---

## 📄 Citation

If you use this repository, please cite our work:

```bibtex
@article{ding2024pheromone,
  title={Pheromone-guided parallel rough hypercuboid attribute reduction algorithm},
  author={Ding, Weiping and Yao, Hongcheng and Ju, Hengrong and Huang, Jiashuang and Jiang, Shu and Chen, Yuepeng},
  journal={Applied Soft Computing},
  volume={156},
  pages={111479},
  year={2024},
  publisher={Elsevier}
}
```


- 🌐 Affiliation: Nantong University, China

