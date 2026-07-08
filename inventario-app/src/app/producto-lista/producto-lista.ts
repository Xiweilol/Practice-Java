import { Component, inject, signal } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  imports: [],
  templateUrl: './producto-lista.html'
})
export class ProductoLista {
  productos = signal<Producto[]>([]);

  private productoServicio = inject(ProductoService);

  private enturador = inject(Router);
  ngOnInit() {
    this.obtenerProductos();
  }

  private obtenerProductos(): void{
    this.productoServicio.obtenerProductosLista().subscribe({
      next: (datos) => {
        this.productos.set(datos);
      },
      error: (errores) =>{
        console.error("Error al obtener los productos",errores);
      }
    });
  }

  editarProducto(id: number){
    this.enturador.navigate(['editar-producto',id]);
  }

  eliminarProducto(id:number){
    this.productoServicio.eliminarProducto(id).subscribe({
      next: (datos) => this.obtenerProductos(),
      error: (errores) => console.log(errores)
    })
  }
}