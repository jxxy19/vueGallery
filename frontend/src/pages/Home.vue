<template>
  <div class="home">

<!--    <section class="py-5 text-center container">-->
<!--      <div class="row py-lg-5">-->
<!--        <div class="col-lg-6 col-md-8 mx-auto">-->
<!--          <h1 class="fw-light">Album example</h1>-->
<!--          <p class="lead text-body-secondary">Something short and leading about the collection below—its contents, the-->
<!--            creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>-->
<!--          <p>-->
<!--            <a href="#" class="btn btn-primary my-2">Main call to action</a>-->
<!--            <a href="#" class="btn btn-secondary my-2">Secondary action</a>-->
<!--          </p>-->
<!--        </div>-->
<!--      </div>-->
<!--    </section>-->

    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div class="col" v-for="(item, idx) in state.items" :key="idx">
<!--            {{item}}-->
            <Card :item="item"/>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import {reactive} from "vue";

export default {
  name: "Home",
  components: {Card},
  setup() {

    const state = reactive({
      items: []

    })
    // axios.get("http:/api/items").then((res) => {
    //   state.items = res.data;
    //   console.log(res);
    // })
    // res 사용 방법
    // data 사용 방법 조금 더 최신 방법이라고 함

    axios.get("http:/api/items").then(({data}) => {
      state.items = data;
      console.log(data);
    })
    return {state}
  }
}
</script>

<style scoped>

</style>