<template>
    <div>
      <h2> {{ username }},登录成功! </h2>
    </div>
</template>

<script>
    export default {
        name: "Result",
        data(){
          return{
            username: ''
          }
        },
      created() {
          this.username = this.$route.params.username
          // console.log(this.$route.params)
      }
    }
</script>

<style scoped>

</style>
