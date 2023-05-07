import 'package:flutter/material.dart';

class ServicoPrestado extends StatelessWidget {
  const ServicoPrestado({super.key});

  PreferredSizeWidget _minhaBarra(String texto) {
    return AppBar(
      shadowColor: Colors.amber,
      backgroundColor: Colors.cyan,
      title: Text(
        texto,
        style: TextStyle(
          fontSize: 38,
          color: Colors.black,
        ),
      ),
    );
  }

  Widget buildSizedBoxWithInput(String labelText) {
    return SizedBox(
      child: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(labelText, style: TextStyle(fontSize: 18)),
            SizedBox(height: 10),
            TextField(
              keyboardType: TextInputType.number,
              decoration: InputDecoration(
                labelText: 'Digite o ' + labelText,
                border: OutlineInputBorder(),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildSizedBoxWithInputTextoFeminino(String labelText) {
    return SizedBox(
      child: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(labelText, style: TextStyle(fontSize: 18)),
            SizedBox(height: 10),
            TextField(
              decoration: InputDecoration(
                labelText: 'Digite a ' + labelText,
                border: OutlineInputBorder(),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget buildSizedBoxWithInputTextoMasculino(String labelText) {
    return SizedBox(
      child: Container(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(labelText, style: TextStyle(fontSize: 18)),
            SizedBox(height: 10),
            TextField(
              decoration: InputDecoration(
                labelText: 'Digite o ' + labelText,
                border: OutlineInputBorder(),
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: _minhaBarra('Dados do Serviço'),
        body: ListView(
          scrollDirection: Axis.vertical,
          children: [
            buildSizedBoxWithInput('Valor do Serviço'),
            buildSizedBoxWithInput('Valor das Deduções'),
            buildSizedBoxWithInput('Valor do PIS'),
            buildSizedBoxWithInput('Valor do COFINS'),
            buildSizedBoxWithInput('Valor do INSS'),
            buildSizedBoxWithInput('Valor do IR'),
            buildSizedBoxWithInput('Valor da CSL'),
            buildSizedBoxWithInput('Valor do Total dos Tributos '),
            buildSizedBoxWithInput('Valor do ISS'),
            buildSizedBoxWithInput('Valor da Alíquota'),
            buildSizedBoxWithInput('Valor do Desconto Condicionado'),
            buildSizedBoxWithInput('Valor do Desconto Incondicionado'),
            buildSizedBoxWithInput('Código CNAE'),
            buildSizedBoxWithInput('Código de Tributação Município'),
            buildSizedBoxWithInputTextoFeminino('Discriminação'),
            buildSizedBoxWithInput('Código do Município de Incidencia'),
            buildSizedBoxWithInput('Número do CPF do tomador'),
            buildSizedBoxWithInput('Número do CNPJ do Tomador'),
            buildSizedBoxWithInputTextoFeminino('Razão Social do Tomador'),
            buildSizedBoxWithInputTextoMasculino('Endereço do Tomador'),
            buildSizedBoxWithInput('Numero do Endereço do Tomador'),
            buildSizedBoxWithInputTextoMasculino('Complemento'),
            buildSizedBoxWithInputTextoMasculino('Bairro'),
            buildSizedBoxWithInput('Código do Munícpio'),
            buildSizedBoxWithInput('Código do Estado'),
            buildSizedBoxWithInput('CEP'),
            buildSizedBoxWithInput('Telefone'),
            buildSizedBoxWithInputTextoMasculino('Email'),
            buildSizedBoxWithInput('Código de Regime Especial de Tributação'),
          ],
        )
        //const Center(child: Text("HOME", style: TextStyle(fontSize: 48))),
        );
  }
}
