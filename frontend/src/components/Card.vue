<template>
  <div class="col">
    <div class="card shadow-sm">
      <span class="img"  :style="{backgroundImage: `url(${item.imgPath})` }"/>
<!--    <img :src="item.imgPath" />-->
      <div class="card-body">
        <p class="card-text">
          <span>{{ item.name }} &nbsp;</span>
          <span class="discount badge bg-danger">{{ item.discountPer }}%</span>
        </p>
        <div class="d-flex justify-content-between align-items-center">
          <button class="btn btn-primary">구입하기</button>
          <small class="price text-muted">{{ util.getNumberFormatted(item.price) }} 원</small>
          <small class="real text-danger">{{ util.getNumberFormatted(util.roundHundred(item.price - (item.price * item.discountPer / 100))) }} 원</small>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import util from "@/js/util";

export default {
  name: "Card",
  props: {
    // item: Object
    item: String
  },
  setup() {
    return {util}
  }
}
</script>

<style scoped>
  .card .img {
    width: 100%;
    display: inline-block;
    height: 430px;
    background-size: cover;
    background-position: center;
  }

  .card .card-body .price {
    text-decoration: line-through;
  }
</style>