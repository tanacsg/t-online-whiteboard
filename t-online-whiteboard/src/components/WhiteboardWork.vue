<script>
export default {
  data() {
    return {
      show: true,
      list: [],
      message: "",
    };
  },
  methods: {
    // Creating function
    append: function () {
      if (this.message) {
        this.list.push(this.message);
        this.message = "";
      }
    },
  },
};
</script>

<template>
  <p>Message is: {{ message }}</p>
  <input v-model="message" placeholder="New text" required />
  <button @click="show = !show">Toggle List</button>
  <button @click="append()">Push Message</button>
  <button @click="list.pop()">Pop Number</button>
  <button @click="list.reverse()">Reverse List</button>

  <ul v-if="show && list.length">
    <li v-for="item of list" :key="item">{{ item }}</li>
  </ul>
  <p v-else-if="list.length">List is not empty, but hidden.</p>
  <p v-else>List is empty.</p>
</template>
